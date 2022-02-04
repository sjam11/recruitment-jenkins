package se.kth.iv1201.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends JpaRepository<User, Integer>{


    
}
