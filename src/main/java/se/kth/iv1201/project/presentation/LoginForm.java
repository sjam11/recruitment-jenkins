package se.kth.iv1201.project.presentation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

 class LoginForm {

    @NotBlank(message = "Please specify user name")

    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "Only letters are allowed")
    @Size(min = 2, max = 30, message = "Name must have min 2 and max 30 characters")

    private String  email;
    private String password;


    /**
     * @return The name of the holderName of the account that will be created.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param holderName The name of the holderName of the account that will be
     *               created.
     */
    public void setPassword(String newElem) {
        password = newElem;
    }
        /**
     * @return The name of the holderName of the account that will be created.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param holderName The name of the holderName of the account that will be
     *               created.
     */
    public void setEmail(String newElem) {
        email = newElem;
    }

}
