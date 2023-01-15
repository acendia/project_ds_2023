package gr.hua.ds.springboot1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;
import org.thymeleaf.model.IModel;

import javax.naming.AuthenticationException;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;

@RestControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(Exception.class)
    public HashMap<String, String> handleException(HttpServletRequest request, Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());

        return response;
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ModelAndView handleMissingPathVariableException(HttpServletRequest request, MissingPathVariableException e) {
        HashMap<String, String> response = new HashMap<>();

        response.put("message", "Required path variable is missing in this request. Please add it to your request.");
        return new ModelAndView("error-page");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFoundResourceException(HttpServletRequest request, NoHandlerFoundException e) {
        return new ModelAndView("error-page");
    }

    @ExceptionHandler({AuthenticationException.class, AccessDeniedException.class, TemplateInputException.class})
    public ModelAndView handleNotFoundResourceException(HttpServletRequest request) {

        return new ModelAndView("error-page");
    }


}