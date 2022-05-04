package se.kth.iv1201.project.presentation;

/**
 * Form bean for creating a user/signing up
 */
class CreateUserForm {

    private String firstName;
    private String lastName;
    private String personNr;
    private String email;
    private String password;
    private String username;


    /** 
     * @return String the firstname of the user
     */
    public String getFirstName() {
        return firstName;
    }

    
    /**     
     * @param newElem The firstname of the user of the account that will be
     *               created.
     */
    public void setFirstName(String newElem) {
        firstName = newElem;
    }

    
    /** 
     * 
     * @return String the username of the user
     */
    public String getUsername() {
        return username;
    }

    
    /** 
     * @param newElem The username of the user of the account that will be
     *               created.
     */
    public void setUsername(String newElem) {
        username = newElem;
    }

    
    /** 
     * @return String Returns the password of the user
     */
    public String getPassword() {
        return password;
    }


    /** 
     * @param newElem The password of the user of the account that will be
     *               created.
     */
    public void setPassword(String newElem) {
        password = newElem;
    }
 
    
    /** 
     * @return String Returns the email of the user
     */
    public String getEmail() {
        return email;
    }


    /** 
     * @param newElem The emial of the user of the account that will be
     *               created.
     */
    public void setEmail(String newElem) {
        email = newElem;
    }

    
    /** 
     * @return String Returns the personnumber of the user
     */
    public String getPersonNr() {
        return personNr;
    }


    /** 
     * @param newElem The personnumber of the user of the account that will be
     *               created.
     */
    public void setPersonNr(String newElem) {
        personNr = newElem;
    }

    
    /** 
     * @return String Returns the lastname of the user
     */
    public String getLastName() {
        return lastName;
    }


    /** 
     * @param newName The lastname of the user of the account that will be
     *               created.
     */
    public void setLastName(String newName) {
        lastName = newName;
    }
}
