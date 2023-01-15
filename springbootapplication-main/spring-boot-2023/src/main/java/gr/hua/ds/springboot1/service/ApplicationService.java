package gr.hua.ds.springboot1.service;

import gr.hua.ds.springboot1.entity.Application;
import gr.hua.ds.springboot1.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;


@Service
public class ApplicationService {

    private final UserService userService;
    private final ApplicationRepository applicationRepository;
    private ArrayList<Application> temp=new ArrayList<>();

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository,UserService userService) {
        this.applicationRepository = applicationRepository;
        this.userService  = userService;
    }

    // get all the applications
    public List<Application> getApplications() {
        return applicationRepository.findAll();
    }

    // get a specific application for the buyer
    public Application getApplicationforBuyer(int id) {
        List<Application> allapl = getApplicationsforBuyer();
        for(int i=0;i<allapl.size();i++){
            if(allapl.get(i).getAid()==id) {
                return allapl.get(i);
            }
        }
        return null;
    }

    // get all applications for a buyer
    public List<Application> getApplicationsforBuyer() {
        List<Application> allBuyerAppl = getApplications();
        ArrayList<Application> seller_application = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        long currentBuyerAfm = userService.getUserByUsername(currentPrincipalName).getAfm();

        for(int i=0;i<allBuyerAppl.size();i++){
            if(allBuyerAppl.get(i).getBuyer_afm_number() == currentBuyerAfm) {
                seller_application.add(allBuyerAppl.get(i));
            }
        }
        return seller_application;
    }

    // get a temporary list of applications.
    public ArrayList<Application> getTemp() {
        return temp;
    }

    // save an application
    public void saveApplication(Application application) {
        applicationRepository.saveAndFlush(application);
    }


    // get Applications of seller users by their username.
    public List<Application> getApplicationsSeller(String username) {
        List<Application> allapl = getApplications();
        ArrayList<Application> seller = new ArrayList<>();
        for(int i=0;i<allapl.size();i++){
            if(allapl.get(i).getUser().getUsername().equals(username)) {
                seller.add(allapl.get(i));
            }
        }
        return seller;
    }

    // method used to connect to the database.
    private Connection connect() {
        String username = "root";
        String password = "123456";
        // mysql connection string
        String url = "jdbc:mysql://localhost:3306/spring_boot_appl?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // method for the deletion of an application.
    public void delete(int id) {
        String sql = "DELETE FROM spring_boot_appl.application WHERE aid = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}