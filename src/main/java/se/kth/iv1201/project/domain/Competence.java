package se.kth.iv1201.project.domain;


import javax.persistence.*;

@Entity
@Table(name = "competence")
public class Competence implements CompetenceDTO{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "competence_id")
    private int competenceID;

    @Column(
        name = "name"
    )
    private String name;

    /**
     * Creates a new intstance of a competnece.
     * @param name the competence.
     */
    public Competence(String name){
        this.name = name;
    }

    /**
     * Required by JPA, should not be used.
     */
    protected Competence(){}

    public void setCompetenceID(int competenceID){
        this.competenceID = competenceID;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCompetenceID(){
        return competenceID;
    }

    public String getName(){
        return name;
    }
}