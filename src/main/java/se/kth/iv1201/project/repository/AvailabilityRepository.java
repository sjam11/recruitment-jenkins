package se.kth.iv1201.project.repository;

import org.springframework.transaction.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.project.domain.Role;
/**
 * Contains all database access concerning Availability of the Applicant.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface AvailabilityRepository extends JpaRepository<Role, Integer>{
    
}
