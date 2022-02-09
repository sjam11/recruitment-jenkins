package se.kth.iv1201.project.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "availability")
public class Availability implements AvailabilityDTO{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private int availabilityID;

    @Column(
        name = "person_id",
        nullable = false
    )
    private int personID;

    @Column(
        name = "from_date",
        nullable = false
    )
    private Date fromDate;

    @Column(
        name = "to_date",
        nullable = false
    )
    private Date toDate;

    /**
     * Creates a new instance for a specific person.
     * @param personID the person id for the person corresponding to this instance
     * @param fromDate start date of availability
     * @param toDate end date of availability
     */
     public Availability(int personID, Date fromDate, Date toDate){
         this.personID = personID;
         this.fromDate = fromDate;
         this.toDate = toDate;
     }

     /**
     * Required by JPA, should not be used.
     */
     protected Availability(){}

     public void setAvailabilityID(int availabilityID){
         this.availabilityID = availabilityID;
     }

     public void setPersonID(int personID){
         this.personID = personID;
     }

     public void setFromDate(Date fromDate){
         this.fromDate = fromDate;
     }

     public void setToDate(Date toDate){
         this.toDate = toDate;
     }

     public int getAvailabilityID(){
        return availabilityID;
    }

    public int getPersonID(){
        return personID;
    }

    public Date getFromDate(){
        return fromDate;
    }

    public Date getToDate(){
        return toDate;
    }
}