package se.kth.iv1201.project.repository;

import org.springframework.transaction.annotation.*;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.project.domain.CompetenceProfile;
import se.kth.iv1201.project.domain.Role;

/**
 * Contains all database access concerning the CompetenceProfile of the Applicant.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CompetenceProfileRepository extends JpaRepository<Role, Integer>{

    CompetenceProfile saveCompetenceProfile(int person_id, int competence_id, BigDecimal years_of_experience);

    CompetenceProfile findCompetenceProfileByPersonID(int id);


}
