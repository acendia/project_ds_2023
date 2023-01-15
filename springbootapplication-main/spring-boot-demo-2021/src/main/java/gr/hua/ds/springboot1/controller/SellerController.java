package gr.hua.ds.springboot1.controller;
import gr.hua.ds.springboot1.entity.Application;
import gr.hua.ds.springboot1.entity.User;
import gr.hua.ds.springboot1.service.ApplicationService;
import gr.hua.ds.springboot1.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/seller")
public class SellerController {
    private final UserService userService;
    private final ApplicationService applicationService;

    public SellerController(UserService userService, ApplicationService applicationService) {
        this.userService = userService;
        this.applicationService = applicationService;
    }

    // get the user input from the form into an application object in the model
    @GetMapping("/application")
    public ModelAndView seeForm(Model model) {
        try {
            Application appl = new Application();
            model.addAttribute("appl", appl);
        } catch (Exception e){
            return new ModelAndView("error-page");
        }

        return new ModelAndView("applicationform-page");
    }

    @PostMapping("/application")
    public ModelAndView seeResults(@ModelAttribute("appl") Application appl) {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();

            appl.setUser(userService.getSellerByUsername(currentPrincipalName));
            appl.setApplicationstatus(0);

            //add in list of applications of user the current application(appl)
            userService.getSellerByUsername(currentPrincipalName).addInApplications(appl);

            // save to server.
            applicationService.saveApplication(appl);
            userService.saveUser(userService.getSellerByUsername(currentPrincipalName));
        } catch (Exception e){
            return new ModelAndView("error-page");
        }
        return new ModelAndView("UserSuccessPage");
    }


    @GetMapping("/myapplications")
    public ModelAndView showApplications(Model model) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            model.addAttribute("allapl", applicationService.getApplicationsSeller(username));
        } catch (Exception e){
            return new ModelAndView("error-page");
        }
        return new ModelAndView("myapplications-page");
    }
    @GetMapping("/deleteAccount")
    public ModelAndView deleteAccount(@ModelAttribute User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        try {
            User currentUser=userService.getUserByUsername(currentUserName);
            userService.removeUser(currentUser);
        } catch (Exception e){
            return new ModelAndView("error-page");
        }
        return new ModelAndView("api-page");
    }

}

