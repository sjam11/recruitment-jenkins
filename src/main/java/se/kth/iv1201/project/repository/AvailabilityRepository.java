package se.kth.iv1201.project.repository;

import org.springframework.transaction.annotation.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.project.domain.Availability;

/**
 * Contains all database access concerning Availability of the Applicant.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface AvailabilityRepository extends JpaRepository<Availability, Integer>{

    List<Availability> findAllByPersonID(int personID);

    @Override
    <S extends Availability> S save(S availability);
}
