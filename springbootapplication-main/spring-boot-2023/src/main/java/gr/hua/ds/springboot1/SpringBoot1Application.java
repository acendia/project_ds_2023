package gr.hua.ds.springboot1;

import gr.hua.ds.springboot1.config.WebSecurityConfig;
import gr.hua.ds.springboot1.entity.User;
import gr.hua.ds.springboot1.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.html.parser.Parser;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringBoot1Application {
	public static void main(String[] args) {

		SpringApplication.run(SpringBoot1Application.class, args);}

}
