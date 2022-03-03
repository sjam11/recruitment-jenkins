package se.kth.iv1201.project.presentation;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.kth.iv1201.project.application.UserService;
import se.kth.iv1201.project.domain.App;
import se.kth.iv1201.project.domain.IllegalUserRegistrationException;
import se.kth.iv1201.project.domain.UserDTO;


@Controller
@Scope("session")
public class UserController {
        
        static final String DEFAULT_PAGE_URL = "/";
        static final String SIGNUP_PAGE_URL = "signup";
        static final String LOGIN_PAGE_URL = "login";
        static final String COMPETENCE_PAGE_URL = "application";
        static final String AVAILABILITY_PAGE_URL = "availability";
        static final String SUMMARY_PAGE_URL = "summary";
        static final String RECRUITER_PAGE_URL = "recruiter";
        static final String NEXT_PAGE_URL = "next";
        static final String PREVIOUS_PAGE_URL = "prev";
        @Autowired
        private UserService service;
        private UserDTO currentUser;
        private HashMap<String,BigDecimal> addedExpertise = new HashMap<String,BigDecimal>();
        private HashMap<Date, Date> addedAvailability = new HashMap<Date,Date>();
        private ArrayList<App> applications;
        private int nextIndex;
        private int amountApplications=9;

    /**
     * No page is specified, redirect to the welcome page.
     * @return A response that redirects the browser to the welcome page.
     */
    @GetMapping(DEFAULT_PAGE_URL)
    public String welcome() {
        return  "welcome";
    }
    /**
     * Redirect to the login page.
     * @param loginForm holds the information the user input in the login view.
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
    public String submitLogin(LoginForm loginForm, CompetenceForm competenceForm, RecruiterFilterForm recruiterFilterForm,Model model) throws IllegalUserRegistrationException{
        currentUser = service.checkUser(loginForm.getUsername(),loginForm.getPassword());
        model.addAttribute("currentUser", currentUser);
        if(currentUser.getRoleID()==1){
            return splitPageSetup(model);
        }
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
    public String submitSignup(CreateUserForm createUserForm, CompetenceForm competenceForm,RecruiterFilterForm recruiterFilterForm,Model model) throws IllegalUserRegistrationException{
        String roleName = "applicant";
        currentUser = service.createUser(createUserForm.getFirstName(),createUserForm.getLastName(),createUserForm.getPersonNr(),createUserForm.getEmail(),createUserForm.getPassword(),roleName,createUserForm.getUsername());
        model.addAttribute("currentUser", currentUser);
       if(currentUser.getRoleID()==1){
        return splitPageSetup(model);
       }
        return "application";
    }

    
    
    /** 
     * @param competenceForm holds the competence information the user input in the application view.
     * @param availabilityForm holds the information the user input in the availability view.
     * @param model refers to the interface model that is sent to the html view application
     * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
     */
    @GetMapping(DEFAULT_PAGE_URL+COMPETENCE_PAGE_URL)
    public String application(CompetenceForm competenceForm, AvailabilityForm availabilityForm ,Model model) throws IllegalUserRegistrationException{
       if(currentUser==null){
        throw new IllegalUserRegistrationException("No user logged in.");
    }
       else{
           return "application";
       }
    }

    
    /** 
     * @param competenceForm holds the competence information the user input in the application view.
     * @param availabilityForm holds the information the user input in the availability view.
     * @param model refers to the interface model that is sent to the html view application
     * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
     */
    @GetMapping(DEFAULT_PAGE_URL+AVAILABILITY_PAGE_URL)
    public String availability(CompetenceForm competenceForm, AvailabilityForm availabilityForm ,Model model) throws IllegalUserRegistrationException{
       if(currentUser==null){
        throw new IllegalUserRegistrationException("No user logged in.");
    }
       else{
           return "availability";
       }
    }
    
    /** 
     * @param competenceForm holds the competence information the user input in the application view.
     * @param availabilityForm holds the information the user input in the availability view.
     * @param model refers to the interface model that is sent to the html view application
     * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
     */
    @GetMapping(DEFAULT_PAGE_URL+SUMMARY_PAGE_URL)
    public String summary(CompetenceForm competenceForm, AvailabilityForm availabilityForm ,Model model) throws IllegalUserRegistrationException{
       if(currentUser==null){
        throw new IllegalUserRegistrationException("No user logged in.");
    }
       else{
           return "summary";
       }
    }
    
    /** 
     * Sends user to the next stage of the application process if they are satisfied with their input. 
     * @param competenceForm holds the competence information the user input in the application view.
     * @param availabilityForm holds the information the user input in the availability view.
     * @param model refers to the interface model that is sent to the html view application
     * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
     */
    @RequestMapping(value=DEFAULT_PAGE_URL+COMPETENCE_PAGE_URL, method = RequestMethod.POST, params = "next")  
    public String confirmCompetence(CompetenceForm competenceForm, AvailabilityForm availabilityForm ,Model model) throws IllegalUserRegistrationException{
        return "availability";
    }

    
    /** 
     * Adds the current compentence selected to the users application
     * @param competenceForm holds the information the user input in the application view.
     * @param model refers to the interface model that is sent to the html view application
     * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
     */
    @RequestMapping(value=DEFAULT_PAGE_URL+COMPETENCE_PAGE_URL, method = RequestMethod.POST, params = "add")  
    public String addCompetence(CompetenceForm competenceForm,Model model) throws IllegalUserRegistrationException{
        addedExpertise.put(competenceForm.getExpertise(), competenceForm.getYears());
        //Resets the fields
        competenceForm.setExpertise("ticket-sales");
        competenceForm.setYears(null);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentExpertise",addedExpertise);
        return "application";
    }

    
    /** 
     * Sends user to the next stage of the application process if they are satisfied with their input. 
     * @param availabilityForm holds the information the user input in the availability view.
     * @param model refers to the interface model that is sent to the html view application
     * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
     */
    @RequestMapping(value=DEFAULT_PAGE_URL+AVAILABILITY_PAGE_URL, method = RequestMethod.POST, params = "next")  
    public String confirmAvailability(AvailabilityForm availabilityForm, Model model) throws IllegalUserRegistrationException{
        model.addAttribute("allExpertise", addedExpertise);
        model.addAttribute("allAvailability", addedAvailability);
        return "summary";
    }

    
    /** 
     * Adds the current availability period to the users application
     * @param availabilityForm holds the information the user input in the availabiity view.
     * @param model refers to the interface model that is sent to the html view application
     * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
     */
    @RequestMapping(value=DEFAULT_PAGE_URL+AVAILABILITY_PAGE_URL, method = RequestMethod.POST, params = "add")  
    public String addAvailability(AvailabilityForm availabilityForm,Model model) throws IllegalUserRegistrationException{
        addedAvailability.put(availabilityForm.getFromDate(), availabilityForm.getToDate());
        model.addAttribute("currentAvailability",addedAvailability);
        return "availability";
    }

    
    /** 
     * Shows a summary of the users application
     * @param model refers to the interface model that is sent to the html view application
     * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
     */
    @PostMapping(DEFAULT_PAGE_URL+SUMMARY_PAGE_URL)
    public String confirm(Model model) throws IllegalUserRegistrationException{
            for (HashMap.Entry<String, BigDecimal> entry : addedExpertise.entrySet()) {
                service.addCompetence(currentUser,entry.getKey(),entry.getValue());
            }
            for (HashMap.Entry<Date,Date> entry : addedAvailability.entrySet()) {  
              service.addAvailability(currentUser,entry.getKey(),entry.getValue());
            }
            model.addAttribute("confirmMsg", "added");
        return "welcome";
     }
     

     
     /** 
      * @param recruiterFilterForm holds the information the recruiter input in the recruiter view to filter the applications.
      * @param model refers to the interface model that is sent to the html view application
      * @return String is the html name of the view that is loaded.
      * @throws IllegalUserRegistrationException 
      */
     @RequestMapping(value={ DEFAULT_PAGE_URL+ RECRUITER_PAGE_URL,DEFAULT_PAGE_URL+NEXT_PAGE_URL,DEFAULT_PAGE_URL+PREVIOUS_PAGE_URL },method=RequestMethod.GET)
     public String recruiter(Model model) throws IllegalUserRegistrationException{
        if(currentUser==null){
            throw new IllegalUserRegistrationException("No user logged in.");
        }
        else if(currentUser.getRoleID()==1){
            return splitPageSetup(model);
        }
       else{
        throw new IllegalUserRegistrationException("Role not authorized.");
       }
     }

     /** 
      * Enables the next page feature to split up the application list
      * into sets of amountApplications, default amount 8.
      * @param recruiterFilterForm holds the information the recruiter input in the recruiter view to filter the applications.
      * @param model refers to the interface model that is sent to the html view application
      * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
      */
      private String splitPageSetup(Model model){
        applications = service.getApplications();
        if(applications.size()>amountApplications) {
            List<App> multiplePages=applications.subList(0,amountApplications);
            model.addAttribute("applications",multiplePages);
            model.addAttribute("nextPage", true);
            nextIndex=amountApplications+1;
        }
        else{
            model.addAttribute("applications",applications);
        }
        return "recruiter";
    }

     /** 
      * Sends the user to the previous page when there are more than a selected amount of applications left to show.
      * @param recruiterFilterForm holds the information the recruiter input in the recruiter view to filter the applications.
      * @param model refers to the interface model that is sent to the html view application
      * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
      */
     @RequestMapping(value={DEFAULT_PAGE_URL+PREVIOUS_PAGE_URL}, method = RequestMethod.POST)  
     public String prevPage(Model model) throws IllegalUserRegistrationException{

        if(currentUser.getRoleID()==1){

            if(nextIndex-1 >amountApplications) {
                List<App> multiplePages=applications.subList(nextIndex-amountApplications,nextIndex);
                model.addAttribute("applications",multiplePages);
                model.addAttribute("prevPage", true);
                model.addAttribute("nextPage", true);
                nextIndex=nextIndex-amountApplications;
                }
            else{
                //Disables the previous button if the first page has been reached. 
                List<App> multiplePages=applications.subList(0,amountApplications);
                model.addAttribute("applications",multiplePages);
                model.addAttribute("prevPage", false);
                model.addAttribute("nextPage", true);
                nextIndex=amountApplications+1;
            }
            return "recruiter";
        }
       else{
        throw new IllegalUserRegistrationException("Role not authorized.");
     }
    }

     
     /** 
      * Sends the user to the next page when there are more than a selected amount of applications left to show.
      * @param recruiterFilterForm holds the information the recruiter input in the recruiter view to filter the applications.
      * @param model refers to the interface model that is sent to the html view application
      * @return String is the html name of the view that is loaded.
     * @throws IllegalUserRegistrationException
      */
     @RequestMapping(value={DEFAULT_PAGE_URL+NEXT_PAGE_URL}, method = RequestMethod.POST)  
     public String nextPage(Model model) throws IllegalUserRegistrationException{
        if(currentUser.getRoleID()==1){
            if(applications.size()-(nextIndex) > amountApplications) {
                List<App> multiplePages=applications.subList(nextIndex,nextIndex+amountApplications);
                model.addAttribute("applications",multiplePages);
                model.addAttribute("prevPage", true);
                model.addAttribute("nextPage", true);
                nextIndex=nextIndex+amountApplications;
                }
            else{
                //Disables the next button if the last page has been reached. 
                List<App> multiplePages=applications.subList(nextIndex,applications.size());
                model.addAttribute("applications",multiplePages);
                model.addAttribute("prevPage", true);
                model.addAttribute("nextPage", false);
            }
            return "recruiter";
        }
       else{
        throw new IllegalUserRegistrationException("Role not authorized.");
    }
     }
}