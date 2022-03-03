package se.kth.iv1201.project.presentation;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import se.kth.iv1201.project.domain.IllegalApplicationException;

@Controller
@ControllerAdvice

public class ExceptionHandlers implements ErrorController {

    
    /** 
     * @param exception Specific error message from exception
     * @param model refers to the interface model that is sent to the html view application
     * @return String is the html name of the view that is loaded.
     */
    @ExceptionHandler(IllegalApplicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(IllegalApplicationException exception, Model model) {
        if (exception.getMessage().toUpperCase().contains("DATABASE")) {
            model.addAttribute("msg", "Cannot reach database, try again later.");}
        else if (exception.getMessage().toUpperCase().contains("ROLE")) {
            model.addAttribute("msg", "Restricted page");}
        else if (exception.getMessage().toUpperCase().contains("ALREADY")) {
            model.addAttribute("msg", "User already exists");}
        else if (exception.getMessage().toUpperCase().contains("SIGNUP")) {
            model.addAttribute("msg", "User not find, Signup and try again.");}
        else if (exception.getMessage().toUpperCase().contains("USER")) {
            model.addAttribute("msg", "Access Denied, Login and try again.");} 
        else {
            model.addAttribute("msg", "Something went wrong, try again");
        }
        return "error";
    }


    
    /** 
    * If no specific exception is thrown this message will be returned.
     * @param exception Specific error message from exception
     * @param model refers to the interface model that is sent to the html view application
     * @return String is the html name of the view that is loaded.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exception, Model model) {
        model.addAttribute("msg", "Something went wrong, try again");
        return "error";
    }
    
    /** 
    * Handels any generic errors with error codes, ex 404.
     * @param request refers to the http request gotten from the page.
     * @param model refers to the interface model that is sent to the html view application
     * @return String is the html name of the view that is loaded.
     */
    @GetMapping("/error")
    public String handleHttpError(HttpServletRequest request, Model model) {
        String statusCode = extractHttpStatusCode(request);
        model.addAttribute("msg", statusCode);
        return "error";
    }

    
    /** 
     * @return String Error code of the requuest.
     */
    private String extractHttpStatusCode(HttpServletRequest request) {
        return request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
