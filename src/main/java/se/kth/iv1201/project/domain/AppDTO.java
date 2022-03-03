package se.kth.iv1201.project.domain;

import java.util.HashMap;

public interface AppDTO {
    HashMap<String,Integer> getCompetence();

    HashMap<String,String> getAvailability();

    UserDTO getPerson();
}
