package se.kth.iv1201.project.repository;

import org.springframework.transaction.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.project.domain.User;

/**
 * Contains all database access concerning Users.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends JpaRepository<User, Integer>{

    /**
     * Looks for a user with specified @param id.
     * Returns User if found, else null.
     * @param id ID of the user to search for.
     * @return User if found, else null.
     */
    User findPersonByPersonID(int id);

    /**
     * Looks for a user with specified pin, person number.
     * Returns User if there is a user with pin, else null.
     * @param pin person number of user
     * @return User if found, else null.
     */
    User findPersonByPin(String pin);

    /**
     * Looks for a user with specified username.
     * @param username Username of user to search for.
     * @return User if username exsists, else null. 
     */
    User findPersonByUsername(String username);

    /**
     * Registers a User into Database.
     */
    @Override
    <S extends User> S save(S user);
    
}
