package se.kth.iv1201.project.repository;

import org.springframework.transaction.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.project.domain.Role;

/**
 * Contains all database access concerning the Role of the User.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RoleRepository extends JpaRepository<Role, Integer>{
    int findRoleIDByName(String roleName);    

    Role findRoleByName(String roleName);

    String findRoleNameByRoleID(int roleID);
}
