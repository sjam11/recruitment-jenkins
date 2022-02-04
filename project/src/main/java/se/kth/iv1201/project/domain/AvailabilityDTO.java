package se.kth.iv1201.project.domain;

public interface AvailabilityDTO {
    int getAvailabilityID();

    int getPersonID();

    Date getFromDate();

    Date getToDate();
}