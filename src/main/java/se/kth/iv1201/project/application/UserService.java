package se.kth.iv1201.project.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.project.domain.IllegalUserRegistrationException;
import se.kth.iv1201.project.domain.User;
import se.kth.iv1201.project.domain.UserDTO;
import se.kth.iv1201.project.repository.UserRepository;
import se.kth.iv1201.project.repository.RoleRepository;


@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class UserService {

    UserService(){}
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserDTO findUserByUserId(int id) {
        return userRepository.findPersonByPersonID(id);
    }

    public UserDTO createUser(String firstName, String lastName, String pin, String email, 
                            String password, String roleName, String username) throws IllegalUserRegistrationException{
        
       /* if(userRepository.findPersonByPIN(pin) != null){
            throw new IllegalUserRegistrationException("Person identification number: " + 
                        pin + " already exist."); 
        }*/

        if(userRepository.findPersonByUsername(username) != null){
            throw new IllegalUserRegistrationException("Username: " + 
                        username + " is already in use."); 
        }

        if(roleRepository.findRoleByName(roleName) != null){
            throw new IllegalUserRegistrationException("No matching role for: " + 
                        roleName + "in the system."); 
        }

        int roleID = roleRepository.findRoleIDByName(roleName);
        User user = new User(firstName, lastName, pin, email, password, roleID, username);
        try{
            userRepository.save(user);
        } catch(Exception e){
            throw new IllegalUserRegistrationException("Could not save user in database");
        }

        return user;
    }
}
