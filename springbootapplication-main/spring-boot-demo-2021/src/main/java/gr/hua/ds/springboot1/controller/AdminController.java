package gr.hua.ds.springboot1.controller;

import gr.hua.ds.springboot1.config.WebSecurityConfig;
import gr.hua.ds.springboot1.entity.User;
import gr.hua.ds.springboot1.service.UserService;

import net.bytebuddy.utility.privilege.GetMethodAction;
import org.apache.tomcat.jni.Error;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    public AdminController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/add")
    public ModelAndView addUser(Model model) {
        model.addAttribute("newuser", new User());
        return new ModelAndView("add-page");
    }
    @PostMapping("/add")
    public ModelAndView createUser(@Validated @ModelAttribute User newuser, Model model){
        model.addAttribute("newuser", newuser);
        try {
            System.out.println(newuser.getPassword());
            newuser.setPassword(passwordEncoder.encode(newuser.getPassword()));
            System.out.println(newuser.getPassword());
            newuser.setEnabled(1);
            userService.saveUser(newuser);
        } catch (Exception e){
            return new ModelAndView("error-page");
        }

        return new ModelAndView("UserSuccessPage");

    }
    @GetMapping("/seeusers")
    public ModelAndView seeUsers(Model model) {
        try {

            model.addAttribute("allusers",userService.getAllUsers());
            model.addAttribute("user", new User());
        } catch (Exception e){
            return new ModelAndView("error-page");
        }

        return new ModelAndView("chooseUser-page");
    }
    @PostMapping("/seeusers")
    public ModelAndView users(Model model,@ModelAttribute User user) {
        try {

            userService.removeUserById(user.getId());
        } catch (Exception e){
            return new ModelAndView("error-page");
        }

        return new ModelAndView("UserSuccessPage");
    }
    @GetMapping(value="/seeusers/alter/{userid}")
    @ResponseBody
    public ModelAndView  alter_form(@PathVariable("userid") String uid,Model model) {
        try {
            int id=Integer.parseInt(uid);
            User auser=userService.getUser(id);
            //String pass=WebSecurityConfig.decrypt(user.getPassword());
            model.addAttribute("auser",auser);
            model.addAttribute("nuser",new User());
            System.out.println(auser.toString());
        } catch (Exception e){
            return new ModelAndView("error-page");
        }
        return new ModelAndView("Alterform-page");
    }
    @PostMapping("/seeusers/alter/{userid}")
    public ModelAndView  doalterUser(@PathVariable("userid") String uid,@ModelAttribute User nuser) {
        try {
            //admin must write again all the element's  even if the value has changed or not
            int id=Integer.parseInt(uid);
            User auser=userService.getUser(id);
            auser.setFirstName(nuser.getFirstName());
            auser.setLastName(nuser.getLastName());
            auser.setUsername(nuser.getUsername());
            auser.setEmail(nuser.getEmail());
            auser.setAfm(nuser.getAfm());
            auser.setAuthority(nuser.getAuthority());


            auser.setEnabled(1);
            userService.saveUser(auser);
        } catch (Exception e){
            return new ModelAndView("error-page");
        }
        return new ModelAndView("UserSuccessPage");
    }


}



