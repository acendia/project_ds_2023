package gr.hua.ds.springboot1.controller;

import gr.hua.ds.springboot1.config.WebSecurityConfig;
import gr.hua.ds.springboot1.entity.User;
import gr.hua.ds.springboot1.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    private UserService userService;

    private final WebSecurityConfig encodepass;

    public HomeController(WebSecurityConfig encodepass, UserService userService) {
        this.encodepass = encodepass;
        this.userService=userService;
    }

    @GetMapping("/")
    public ModelAndView greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

        return new ModelAndView("api-page");
    }
    @RequestMapping("/seller")
    public ModelAndView sellerDashboard() {
        return new ModelAndView("seller-page");
    }
    @RequestMapping("/buyer")
    public ModelAndView buyerDashboard() {
        return new ModelAndView("buyer-page");
    }
    @RequestMapping("/admin")
    public ModelAndView adminDashboard() {
        return new ModelAndView("admin-page");
    }
    @RequestMapping("/api")
    public ModelAndView hello() {
        return new ModelAndView("api-page");
    }
    @RequestMapping("/home")
    public ModelAndView homepage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser=userService.getUserByUsername(currentUserName);
        String role=currentUser.getAuthority();
        if (role.equals("ROLE_SELLER")){
            return new ModelAndView("seller-page");
        } else if (role.equals("ROLE_BUYER")){
            return new ModelAndView("buyer-page");
        } else {
            return new ModelAndView("admin-page");
        }
    }
    @GetMapping("/register")
    public ModelAndView register(Model model) {
        model.addAttribute("user",new User());
        return new ModelAndView("register-page");
    }

    @PostMapping("/register")
    public ModelAndView postRegister(@ModelAttribute User user) {
        user.setEnabled(1);

        try {
            user.setPassword(encodepass.passwordEncoder().encode(user.getPassword()));
            userService.saveUser(user);
        } catch (Exception e){
            return new ModelAndView("error-page");
        }

        return new ModelAndView("UserSuccessPage");
    }




}
