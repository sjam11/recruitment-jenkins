package se.kth.iv1201.project.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import se.kth.iv1201.project.application.UserService;
import se.kth.iv1201.project.domain.IllegalUserRegistrationException;
import se.kth.iv1201.project.domain.UserDTO;


@Controller
@Scope("session")
public class UserController {
        
        static final String DEFAULT_PAGE_URL = "/";
        static final String LOGIN_PAGE_URL = "login";
        static final String HOME_PAGE_URL = "application";
        static final String SIGNUP_PAGE_URL = "signup";
        @Autowired
        private UserService service;
        private UserDTO currentUser;



    /**
     * No page is specified, redirect to the welcome page.
     *
     * @return A response that redirects the browser to the welcome page.
     */
    @GetMapping(DEFAULT_PAGE_URL)
    public String welcome() {
        return  "welcome";
    }
    /**
     * Redirect to the login page.
     *
     * @return A response that redirects the browser to the login page.
     */
    @GetMapping(DEFAULT_PAGE_URL+LOGIN_PAGE_URL)
    public String login(LoginForm loginForm) {
        return  "login";
    }

    
    /** 
     * Checks the login info against the database and sends user to application page.
     * @param loginForm holds the information the user input in the login view.
     * @param model refers to the interface model that is sent to the html view application 
     * @return String is the html name of the view that is loaded.
     */
    @PostMapping(DEFAULT_PAGE_URL+LOGIN_PAGE_URL) 
    public String application(LoginForm loginForm, Model model){
        currentUser = service.CheckUserAndRole(loginForm.getUsername(),loginForm.getPassword());
        model.addAttribute("currentUser", currentUser);
        return "application";
    }
    
    /** 
     * Loads the signup page.
     * @param createUserForm holds the information the user input in the signup view.
     * @return String is the html name of the view that is loaded.
     */
    @GetMapping(DEFAULT_PAGE_URL+SIGNUP_PAGE_URL)
    public String signup(CreateUserForm createUserForm) {
        return  "signup";
    }

    
    /** 
     * Checks the signup info against the database and sends user to application page.
     * @param createUserForm holds the information the user input in the signup view.
     * @param model refers to the interface model that is sent to the html view application 
     * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
     */
    @PostMapping(DEFAULT_PAGE_URL+SIGNUP_PAGE_URL)  
    public String application(CreateUserForm createUserForm, Model model) throws IllegalUserRegistrationException{
        String roleName = "Applicant";
        currentUser = service.createUser(createUserForm.getFirstName(),createUserForm.getLastName(),createUserForm.getPersonNr(),createUserForm.getEmail(),createUserForm.getPassword(),roleName,createUserForm.getUsername());
            model.addAttribute("currentUser", currentUser);
            return "application";
        }




}