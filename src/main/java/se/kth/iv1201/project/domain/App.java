package se.kth.iv1201.project.domain;

import java.sql.Date;
import java.util.HashMap;

public class App {

    private User person;
    private HashMap<Date, Date> availability;
    private HashMap<String, Integer> competence;

    /**
     * Creates an instance of application for a user with their competence and availibility
     * @param person the person.
     * @param competence their competence.
     * @param availability their availability period.
     */
    public App(User person, HashMap<String,Integer> competence, HashMap<Date,Date> availability){
        this.person = person;
        this.competence = competence;
        this.availability = availability;
    }
    public void setAvailability(HashMap<Date,Date> availability){
        this.availability = availability;
    }

    public void setPerson(User person){
        this.person = person;
    }

    public void setCompence(HashMap<String,Integer> competence){
        this.competence = competence;
    }

    public HashMap<String,Integer> getCompetence(){
        return competence;
    }
    public HashMap<Date,Date> getAvailability(){
        return availability;
    }
    public UserDTO getPerson(){
        return person;
    }


}
