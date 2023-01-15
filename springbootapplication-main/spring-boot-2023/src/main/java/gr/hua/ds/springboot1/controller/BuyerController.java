package gr.hua.ds.springboot1.controller;

import gr.hua.ds.springboot1.entity.Application;
import gr.hua.ds.springboot1.entity.User;
import gr.hua.ds.springboot1.service.ApplicationService;
import gr.hua.ds.springboot1.service.UserService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
    private  final UserService userService;
    private final ApplicationService applicationService;

    public Application tempApp;

    public BuyerController(UserService userService, ApplicationService applicationService) {
        this.userService = userService;
        this.applicationService = applicationService;
    }

    @GetMapping("/confirmation")
    public ModelAndView seeAllAppl(Model model) {
        try {
            model.addAttribute("allapl",applicationService.getApplicationsforBuyer());
            model.addAttribute("appl", new Application());
        } catch (Exception e){
            return new ModelAndView("error-page");
        }

        return new ModelAndView("confirmation-page");
    }
    @PostMapping("/confirmation")
    public ModelAndView ChooseAppl(@Validated @ModelAttribute Application app, Model model){
        try {
            Application app1=applicationService.getApplicationforBuyer(app.getAid());
            tempApp = app1;
            model.addAttribute("applica", app1);
        } catch (Exception e){
            return new ModelAndView("error-page");
        }
        return new ModelAndView("buyer-reject-accept");
    }

    @GetMapping("/confirmation/accept")
    public ModelAndView acceptAppl() {
        try {
            tempApp.setApplicationstatus(1);
            applicationService.saveApplication(tempApp);
        } catch (Exception e){
            return new ModelAndView("error-page");
        }

        return new ModelAndView("UserSuccessPage");
    }

    @GetMapping("/confirmation/deny")
    public ModelAndView denyAppl() {
        try {
            tempApp.getUser().rmvFromApplications(tempApp);
            applicationService.delete(tempApp.getAid());
        } catch (Exception e){
            return new ModelAndView("error-page");
        }
        return new ModelAndView("UserSuccessPage");
    }


}

