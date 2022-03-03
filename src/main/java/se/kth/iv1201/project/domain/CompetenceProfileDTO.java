package se.kth.iv1201.project.domain;

import java.math.BigDecimal;

public interface CompetenceProfileDTO {
    /**
     * get the id of competence profile
     * @return id of competence profile
     */
    int getCompetenceProfileID();
    
    /**
     * gets person id for person with competence profile
     * @return id of person
     */
    int getPersonID();

    /**
     * gets id of competence in competence profile
     * @return id of competence
     */
    int getCompetenceID();

    /**
     * gets years of experience for competence profile
     * @return years of experience
     */
    BigDecimal getYearOfExperience();
}
