package se.kth.iv1201.project.domain;

public interface ApplicationDTO {
    
    /**
     * gets application id
     * @return id of application
     */
    int getApplicationID();

    /**
     * gets id for person/user
     * @return id of person/user
     */
    int getPersonID();

    /**
     * gets status for application
     * @return status of application
     */
    String getStatus();
}
