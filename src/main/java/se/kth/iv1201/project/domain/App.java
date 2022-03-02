package se.kth.iv1201.project.domain;

import java.util.HashMap;

public class App {

    private User person;
    private HashMap<String, String> availability;
    private HashMap<String, Integer> competence;

    /**
     * 
     * @param person
     * @param competence
     * @param availability
     */
    public App(User person, HashMap<String,Integer> competence, HashMap<String,String> availability){
        this.person = person;
        this.competence = competence;
        this.availability = availability;
    }
    public void setAvailability(HashMap<String,String> availability){
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
    public HashMap<String,String> getAvailability(){
        return availability;
    }
    public UserDTO getPerson(){
        return person;
    }


}
