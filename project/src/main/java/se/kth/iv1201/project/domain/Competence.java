package se.kth.iv1201.project.domain;


import javax.persistence.*;

@Entity
@Table(name = "competence")
public class Competence implements CompetenceDTO{
    @ID
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "competence_id")
    private int competenceID;

    @Column(
        name = "name"
    )
    private String name;

    public Competence(String name){
        this.name = name;
    }

    public int setCompetenceID(int competenceID){
        this.competenceID = competenceID;
    }

    public String setName(String name){
        this.name = name;
    }

    public int getCompetenceID(){
        return competenceID;
    }

    public String getName(){
        return name;
    }
}