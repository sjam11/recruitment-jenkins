package se.kth.iv1201.project.domain;
import java.sql.Date;

public interface AvailabilityDTO {
    /**
     * returns is for id for availability period
     * @return id of availability
     */
    int getAvailabilityID();

    /**
     * gets id for person/user
     * @return id of person/user
     */
    int getPersonID();

    /**
     * gets the from date for an availability period
     * @return the from date
     */
    Date getFromDate();

    /**
     * gets the to date for an availability period 
     * @return the to date 
     */
    Date getToDate();
}