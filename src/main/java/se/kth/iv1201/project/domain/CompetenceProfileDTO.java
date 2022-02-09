package se.kth.iv1201.project.domain;

import java.math.BigDecimal;

public interface CompetenceProfileDTO {
    int getCompetenceProfileID();
    
    int getPersonID();

    int getCompetenceID();

    BigDecimal getYearOfExperience();
}
