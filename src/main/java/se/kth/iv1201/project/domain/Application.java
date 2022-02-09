package se.kth.iv1201.project.domain;

import javax.persistence.*;


@Entity
@Table(name = "application")
public class Application implements ApplicationDTO{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "application_id")
    private int applicationID;

    @Column(
        name = "person_id",
        nullable = false
    )
    private int personID;

    @Column(
        name = "status",
        nullable = false
    )
    private String status;

    /**
     * Creates a new intstance with the specified person id and application status.
     * @param personID the users account id.
     * @param status the status of the application accepted/not handled/declined.
     */
    public Application(int personID, String status){
        this.personID = personID;
        this.status = status;
    }

    /**
     * Required by JPA, should not be used.
     */
    protected Application(){}

    public void setApplicationID(int applicationID){
        this.applicationID = applicationID;
    }

    public void setPersonID(int personID){
        this.personID = personID;
     }

     public void setStatus(String status){
        this.status = status;
     }

    public int getApplicationID(){
        return applicationID;
    }

    public int getPersonID(){
        return personID;
    }

    public String getStatus(){
        return status;
    }
}

