package se.kth.iv1201.project.repository;

import org.springframework.transaction.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.project.domain.Role;

/**
 * Contains all database access concerning the Competence of the applicant.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CompetenceRepository extends JpaRepository<Role, Integer>{

    int getCompetenceID(String name);
}
