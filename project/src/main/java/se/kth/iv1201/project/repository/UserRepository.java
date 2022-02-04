package se.kth.iv1201.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.project.domain.User;

@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends JpaRepository<User, Integer>{


    
}
