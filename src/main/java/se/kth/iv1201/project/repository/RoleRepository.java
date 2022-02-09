package se.kth.iv1201.project.repository;

import org.springframework.transaction.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.project.domain.Role;

@Repository
@NoRepositoryBean
@Transactional(propagation = Propagation.MANDATORY)
public interface RoleRepository extends JpaRepository<Role, Integer>{
    int findRoleIdByRoleName(String roleName);    

    Role findRoleByRoleName(String roleName);
}
