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

    /**
     * Fetches role id for specific role name
     * @param roleName name of role
     * @return id of role
     */
    int findRoleIDByName(String roleName);    

    /**
     * Gets role object with specified role name
     * @param roleName name of role object to fetch
     * @return role
     */
    Role findRoleByName(String roleName);

    /**
     * Gets name of role with specified role id
     * @param roleID id of role to fetch
     * @return role name
     */
    String findRoleNameByRoleID(int roleID);
}
