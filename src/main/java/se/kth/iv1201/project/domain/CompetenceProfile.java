package se.kth.iv1201.project.domain;

import javax.persistence.*;



@Entity
@Table(name = "competence_profile")
public class CompetenceProfile implements CompetenceProfileDTO{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "competence_profile_id")
    private int competenceProfileID;

    @Column(
        name = "person_id",
        nullable = false
    )
    private int personID;

    @Column(
        name = "competence_id",
        nullable = false
    )
    private int competenceID;

    @Column(
        name = "years_of_experience",
        nullable = false
    )
    private int yearsOfExperience;

    public CompetenceProfile(int personID, int competenceID, int yearsOfExperience){
        this.personID = personID;
        this.competenceID = competenceID;
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setCompetenceProfileID(int competenceProfileID){
        this.competenceProfileID = competenceProfileID;
    }

    public void setPersonID(int personID){
        this.personID = personID;
    }

    public void setCompetenceID(int competenceID){
        this.competenceID = competenceID;
    }

    public void setYearsOfExperience(int yearsOfExperience){
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getCompetenceProfileID(){
        return competenceProfileID;
    }

    public int getPersonID(){
        return personID;
    }

    public int getCompetenceID(){
        return competenceID;
    }

    public int getYearOfExperience(){
        return yearsOfExperience;
    }
}