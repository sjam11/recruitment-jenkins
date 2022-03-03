package se.kth.iv1201.project.domain;

import java.util.HashMap;

public class App {

    private User person;
    private HashMap<String, String> availability;
    private HashMap<String, Integer> competence;

    public App(User person, HashMap<String,Integer> competence, HashMap<String,String> availability){
        this.person = person;
        this.competence = competence;
        this.availability = availability;
    }
    
    /** 
     * @param availability the dates they are availible to work
     */
    public void setAvailability(HashMap<String,String> availability){
        this.availability = availability;
    }

    
    /** 
     * @param person the users personal information
     */
    public void setPerson(User person){
        this.person = person;
    }

    
    /** 
     * @param competence the area of expertise and the amount of years in that field.
     */
    public void setCompence(HashMap<String,Integer> competence){
        this.competence = competence;
    }

    
    /** 
     * @return HashMap<String, Integer> the area of expertise and the amount of years in that field.
     */
    public HashMap<String,Integer> getCompetence(){
        return competence;
    }
    
    /** 
     * @return HashMap<String, String> the dates they are availible to work
     */
    public HashMap<String,String> getAvailability(){
        return availability;
    }
    
    /** 
     * @return UserDTO the personal information about the user
     */
    public UserDTO getPerson(){
        return person;
    }


}
