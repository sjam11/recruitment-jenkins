package se.kth.iv1201.project.repository;

import org.springframework.transaction.annotation.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.project.domain.CompetenceProfile;

/**
 * Contains all database access concerning the CompetenceProfile of the Applicant.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile, Integer>{


    /**
     * Finds competence profile for specific person
     * @param id of person to find competence profile of
     * @return competence profile
     */
    CompetenceProfile findCompetenceProfileByPersonID(int id);

    /**
     * Returns all competence for a specific person
     * @param personID
     * @return list of competence for person
     */
    List<CompetenceProfile> findAllByPersonID(int personID);

    /**
     * Saves competence profile to DB
     */
    @Override
    <S extends CompetenceProfile> S save(S competenceProfile);

}
