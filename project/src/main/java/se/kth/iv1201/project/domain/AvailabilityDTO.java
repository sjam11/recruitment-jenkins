package se.kth.iv1201.project.domain;
import java.sql.Date;

public interface AvailabilityDTO {
    int getAvailabilityID();

    int getPersonID();

    Date getFromDate();

    Date getToDate();
}