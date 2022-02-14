package se.kth.iv1201.project.presentation;

 class LoginForm {

    private String  username;
    private String password;



    
    /** 
     * @return String the password of the user
     */
    public String getPassword() {
        return password;
    }

    
    /** 
     * @param newElem The password of the user of the account that will be
     *               created.     
     * */
    public void setPassword(String newElem) {
        password = newElem;
    }

    
    /** 
     * @return String the username of the user
     */
    public String getUsername() {
        return username;
    }


    
    
    /** 
     * @param newElem The username of the user of the account that will be
     *               created.     
     * */
    public void setUsername(String newElem) {
        username = newElem;
    }

}
