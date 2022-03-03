package se.kth.iv1201.project.domain;

public interface UserDTO {
    /**
     * gets person id for a user
     * @return id of person
     */
    int getPersonID();

    /**
     * gets users first name
     * @return first name of user
     */
    String getFirstName();
    
    /**
     * gets last name of user
     * @return last name of user
     */
    String getLastName();

    /**
     * gets personal identification number of user
     * @return pin for user
     */
    String getPIN();

    /**
     * gets email of user
     * @return email of user
     */
    String getEmail();

    /**
     * gets password for user
     * @return users password
     */
    String getPassword();

    /**
     * gets user role id
     * @return role id of user
     */
    int getRoleID();

    /**
     * gets the users username
     * @return username of user
     */
    String getUsername();
}
