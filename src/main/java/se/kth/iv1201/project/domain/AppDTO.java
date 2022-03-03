package se.kth.iv1201.project.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;

public interface AppDTO {
    /**
     * gets competences for a user
     * @return hashmap with users competences and years of experience
     */
    HashMap<String,BigDecimal> getCompetence();

    /**
     * gets all availability periods for a user 
     * @return hashmap with users availability periods 
     */
    HashMap<Date,Date> getAvailability();

    /**
     * Gets person
     * @return user object
     */
    UserDTO getPerson();
}
