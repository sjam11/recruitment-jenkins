package se.kth.iv1201.project.domain;

import javax.persistence.*;

@Entity
@Table(name = "applicant")
public class User implements UserDTO{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int personID;

    @Column(
        name = "name",
        nullable = false
    )
    private String firstName;

    @Column(
        name = "surname",
        nullable = false
    )
    private String lastName;

    @Column(
        name = "pnr",
        nullable = false
    )
    private String pin;

    @Column(
        name = "email",
        nullable = false
    )
    private String email;

    @Column(
        name = "password",
        nullable = false
    )
    private String password;

    @Column(
        name = "role_id"
    )
    private int roleID;

    @Column(
        name = "username",
        nullable = false
    )
    private String username;

    public User(String firstName, String lastName, String pin, String email, String password, int roleID, String username){
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.username = username;
    }

    public void setPersonID(int personID){
        this.personID = personID;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setPIN(String pin){
        this.pin = pin;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRoleID(int roleID){
        this.roleID = roleID;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public int getPersonID(){
        return personID;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPIN(){
        return pin;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public int roleID(){
        return roleID;
    }

    public String getUsername(){
        return username;
    }
}