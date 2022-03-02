package se.kth.iv1201.project.presentation;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.kth.iv1201.project.application.ApplicationService;
import se.kth.iv1201.project.domain.IllegalApplicationException;
import se.kth.iv1201.project.domain.UserDTO;


@Controller
@Scope("session")
public class UserController {
        
        static final String DEFAULT_PAGE_URL = "/";
        static final String LOGIN_PAGE_URL = "login";
        static final String HOME_PAGE_URL = "application";
        static final String SIGNUP_PAGE_URL = "signup";
        @Autowired
        private ApplicationService service;
        private UserDTO currentUser;
        private HashMap<String,BigDecimal> addedExpertise = new HashMap<String,BigDecimal>();
        private HashMap<Date, Date> addedAvailability = new HashMap<Date,Date>();



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
     * @throws IllegalUserRegistrationException
     */
    @PostMapping(DEFAULT_PAGE_URL+LOGIN_PAGE_URL) 
    public String submitLogin(LoginForm loginForm, PositionForm positionForm, Model model) throws IllegalApplicationException{
        currentUser = service.checkUser(loginForm.getUsername(),loginForm.getPassword());
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
    public String submitSignup(CreateUserForm createUserForm, PositionForm positionForm,Model model) throws IllegalApplicationException{
        String roleName = "applicant";
        currentUser = service.createUser(createUserForm.getFirstName(),createUserForm.getLastName(),createUserForm.getPersonNr(),createUserForm.getEmail(),createUserForm.getPassword(),roleName,createUserForm.getUsername());
        model.addAttribute("currentUser", currentUser);
        return "application";
    }

    @GetMapping("/application") 
    public String application(PositionForm positionForm) {
        return  "welcome";
    } 


    @GetMapping("/summary")
    public String summary(){
        return "welcome";
    }


    @RequestMapping(value=DEFAULT_PAGE_URL+"/application", method = RequestMethod.POST, params = "next")  
    public String confirmCompetence(PositionForm positionForm, AvailabilityForm availabilityForm ,Model model) throws IllegalApplicationException{
        //addedExpertise.put(positionForm.getExpertise(), positionForm.getYears()); Remove comment if current expertise should be added when going to next page.
        // Add check if addedExpertise is empty. 
        return "availability";
    }

    @RequestMapping(value=DEFAULT_PAGE_URL+"/application", method = RequestMethod.POST, params = "add")  
    public String addCompetence(PositionForm positionForm,Model model) throws IllegalApplicationException{
        addedExpertise.put(positionForm.getExpertise(), positionForm.getYears());
        //Resets the fields
        positionForm.setExpertise("ticket-sales");
        positionForm.setYears(null);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentExpertise",addedExpertise);
        return "application";
    }

    @RequestMapping(value=DEFAULT_PAGE_URL+"/availability", method = RequestMethod.POST, params = "next")  
    public String confirmAvailability(AvailabilityForm availabilityForm, Model model) throws IllegalApplicationException{
        // addedAvailability.put(availabilityForm.getFromDate(), availabilityForm.getToDate()); Remove comment if current availability should be added when going to next page.
        model.addAttribute("allExpertise", addedExpertise);
        model.addAttribute("allAvailability", addedAvailability);
        return "summary";
    }

    @RequestMapping(value=DEFAULT_PAGE_URL+"/availability", method = RequestMethod.POST, params = "add")  
    public String addAvailability(AvailabilityForm availabilityForm,Model model) throws IllegalApplicationException{
        addedAvailability.put(availabilityForm.getFromDate(), availabilityForm.getToDate());
        model.addAttribute("currentAvailability",addedAvailability);
        return "availability";
    }

    @PostMapping("/summary")
    public String confirm(Model model) throws IllegalApplicationException{
        for (HashMap.Entry<String, BigDecimal> entry : addedExpertise.entrySet()) {
            service.addCompetence(currentUser,entry.getKey(),entry.getValue());
        }
        for (HashMap.Entry<Date,Date> entry : addedAvailability.entrySet()) {  
          service.addAvailability(currentUser,entry.getKey(),entry.getValue());
        }
        //Add confirmation message
        model.addAttribute("confirmMsg", "added");
        return "welcome";
     }
}