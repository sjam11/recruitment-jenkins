package se.kth.iv1201.project.repository;

import org.springframework.transaction.annotation.*;

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
     * Returns the name of a competence.
     * @param competence_id the id of the competence, to get the name of
     * @return name of competences
     */
    String getCompetenceNameByCompetenceID(int competence_id);

    /**
     * Returns competence from competence name
     * @param name of competence
     * @return Competence
     */
    Competence findCompetenceByName(String name);
}
