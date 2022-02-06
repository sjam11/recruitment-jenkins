package se.kth.iv1201.project.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.project.domain.UserDTO;
import se.kth.iv1201.project.repository.UserRepository;

@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public UserDTO findUserByUserId(int id) {
        return userRepository.findUserByUserId(id);
    }
}
