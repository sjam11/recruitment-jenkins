package se.kth.iv1201.project.domain;

import javax.persistence.*;

@Entity
@Table(name = "competence_profile")
public class Role implements RoleDTO{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleID;

    @Column(
        name = "name",
        nullable = false
    )
    private String name;

    public Role(String name){
        this.name = name;
    }

    public void setRoleID(int roleID){
        this.roleID = roleID;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getRoleID(){
        return roleID;
    }

    public String getName(){
        return name;
    }
}