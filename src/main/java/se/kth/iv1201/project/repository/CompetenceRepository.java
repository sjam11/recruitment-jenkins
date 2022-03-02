package se.kth.iv1201.project.repository;

import org.springframework.transaction.annotation.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.project.domain.Competence;

/**
 * Contains all database access concerning the Competence of the applicant.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CompetenceRepository extends JpaRepository<Competence, Integer>{

    /**
     * Returns all competence for a specific person
     * @param personID
     * @return list of competence for person
     */
    List<Competence> findAllByPersonID(int personID);

    /**
     * Returns competence from competence name
     * @param name of competence
     * @return Competence
     */
    Competence findCompetenceByName(String name);
}
