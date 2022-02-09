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
        static final String HOME_PAGE_URL = "/application";
        @Autowired
        private UserService service;
        private UserDTO currentUser;

    /**
     * No page is specified, redirect to the login page.
     *
     * @return A response that redirects the browser to the welcome page.
     */
    @GetMapping("/")
    public String login(LoginForm loginForm) {
        return  "login";
    }

    @PostMapping("/") 
    public String application(LoginForm loginForm, Model model){
        currentUser = service.CheckUserAndRole(loginForm.getEmail(),loginForm.getPassword());
        model.addAttribute("currentUser", currentUser);
        return "application";
    }
    @GetMapping("/signup")
    public String signup(CreateUserForm createUserForm) {
        return  "signup";
    }

    @PostMapping("/signup") // When createUserAndCheckRole is finished change the type of currentUser to UserDTO. 
    public String application(CreateUserForm createUserForm, Model model) throws IllegalUserRegistrationException{
        String roleName = "Applicant";
        currentUser = service.createUser(createUserForm.getFirstName(),createUserForm.getLastName(),createUserForm.getPersonNr(),createUserForm.getEmail(),createUserForm.getPassword(),roleName,createUserForm.getUsername());
            model.addAttribute("currentUser", currentUser);
        System.out.println(currentUser.getFirstName());
        return "application";
    }

}