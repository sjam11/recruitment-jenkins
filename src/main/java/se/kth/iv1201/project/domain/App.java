package se.kth.iv1201.project.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;

public class App {

    private User person;
    private HashMap<Date, Date> availability;
    private HashMap<String, BigDecimal> competence;

    /**
     * Creates an instance of application for a user with their competence and availibility
     * @param person the person.
     * @param competence their competence.
     * @param availability their availability period.
     */
    public App(User person, HashMap<String,BigDecimal> competence, HashMap<Date,Date> availability){
        this.person = person;
        this.competence = competence;
        this.availability = availability;
    }

    /** 
     * @param availability the dates they are availible to work
     */
    public void setAvailability(HashMap<Date,Date> availability){
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
    public void setCompence(HashMap<String,BigDecimal> competence){
        this.competence = competence;
    }

    /** 
     * @return HashMap<String, Integer> the area of expertise and the amount of years in that field.
     */
    public HashMap<String,BigDecimal> getCompetence(){
        return competence;
    }

    /** 
     * @return HashMap<Date, Date> the dates they are availible to work
     */
    public HashMap<Date,Date> getAvailability(){
        return availability;
    }
    
    /** 
     * @return UserDTO the personal information about the user
     */
    public UserDTO getPerson(){
        return person;
    }
}
