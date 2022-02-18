package se.kth.iv1201.project.presentation;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.kth.iv1201.project.application.UserService;
import se.kth.iv1201.project.domain.CompetenceProfile;
import se.kth.iv1201.project.domain.CompetenceProfileDTO;
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
        private CompetenceProfileDTO currentApplication;
        private HashMap<String,BigDecimal> addedExpertise = new HashMap<String,BigDecimal>();



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
     * Loads the signup page.
     * @param createUserForm holds the information the user input in the signup view.
     * @return String is the html name of the view that is loaded.
     */
    @GetMapping(DEFAULT_PAGE_URL+SIGNUP_PAGE_URL)
    public String signup(CreateUserForm createUserForm) {
        return  "signup";
    }
    
    /** 
     * Checks the login info against the database and sends user to application page.
     * @param loginForm holds the information the user input in the login view.
     * @param model refers to the interface model that is sent to the html view application 
     * @return String is the html name of the view that is loaded.
     */
    @PostMapping(DEFAULT_PAGE_URL+LOGIN_PAGE_URL) 
    public String submitLogin(LoginForm loginForm, PositionForm positionForm,Model model){
        currentUser = service.CheckUserAndRole(loginForm.getUsername(),loginForm.getPassword());
        model.addAttribute("currentUser", currentUser);
        return "application";
    }
    
    /** 
     * Checks the signup info against the database and sends user to application page.
     * @param createUserForm holds the information the user input in the signup view.
     * @param model refers to the interface model that is sent to the html view application 
     * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
     */
    @PostMapping(DEFAULT_PAGE_URL+SIGNUP_PAGE_URL)  
    public String submitSignup(CreateUserForm createUserForm, PositionForm positionForm,Model model) throws IllegalUserRegistrationException{
        String roleName = "Applicant";
        currentUser = service.createUser(createUserForm.getFirstName(),createUserForm.getLastName(),createUserForm.getPersonNr(),createUserForm.getEmail(),createUserForm.getPassword(),roleName,createUserForm.getUsername());
        model.addAttribute("currentUser", currentUser);
        return "application";
        }

    @GetMapping("/application") 
    public String application(PositionForm positionForm) {
        return  "application";
    } 


    @GetMapping("/summary")
    public String summary(){
        return "summary";
    }


    @RequestMapping(value=DEFAULT_PAGE_URL+"/application", method = RequestMethod.POST, params = "next")  
    public String confirmCompetence(PositionForm positionForm, Model model) throws IllegalUserRegistrationException{
        addedExpertise.put(positionForm.getExpertise(), positionForm.getYears());
        model.addAttribute("allExpertise", addedExpertise);
        return "summary";
    }

    @RequestMapping(value=DEFAULT_PAGE_URL+"/application", method = RequestMethod.POST, params = "add")  
    public String addCompetence(PositionForm positionForm,Model model) throws IllegalUserRegistrationException{
        addedExpertise.put(positionForm.getExpertise(), positionForm.getYears());
        //Resets the fields
        positionForm.setExpertise("ticket");
        positionForm.setYears(null);
        model.addAttribute("currentUser", currentUser);

        model.addAttribute("currentExpertise",addedExpertise);
        return "application";
    }

    @PostMapping("/summary")
    public String confirm(Model model) throws IllegalUserRegistrationException{
        for (HashMap.Entry<String, BigDecimal> entry : addedExpertise.entrySet()) {
            int competenceID = service.getCompetenceID(entry.getKey());
            BigDecimal years = entry.getValue();
            CompetenceProfile competenceProfile = new CompetenceProfile(currentUser.getPersonID(), competenceID, years);
            currentApplication = service.addCompetence(competenceProfile);
        }
        return "welcome";
     }
}