package se.kth.iv1201.project.application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.project.domain.IllegalUserRegistrationException;
import se.kth.iv1201.project.domain.User;
import se.kth.iv1201.project.domain.UserDTO;
import se.kth.iv1201.project.repository.UserRepository;
import se.kth.iv1201.project.repository.AvailabilityRepository;
import se.kth.iv1201.project.repository.CompetenceProfileRepository;
import se.kth.iv1201.project.repository.RoleRepository;


@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;
    @Autowired
    private AvailabilityRepository availabilityRepository;

    public UserDTO findUserByUserId(int id) {
        return userRepository.findPersonByPersonID(id);
    }

    public UserDTO createUser(String firstName, String lastName, String pin, String email, 
                            String password, String roleName, String username) throws IllegalUserRegistrationException{
        
        if(userRepository.findPersonByPin(pin) != null){
            throw new IllegalUserRegistrationException("Person identification number: " + 
                        pin + " already exist."); 
        }

        if(userRepository.findPersonByUsername(username) != null){
            throw new IllegalUserRegistrationException("Username: " + 
                        username + " is already in use."); 
        }

        if(roleRepository.findRoleByName(roleName) != null){
            throw new IllegalUserRegistrationException("No matching role for: " + 
                        roleName + "in the system."); 
        }

        //int roleID = roleRepository.findRoleIDByName(roleName);
        User user = new User(firstName, lastName, pin, email, MD5(password), 1, username);
        try{
            userRepository.save(user);
        } catch(Exception e){
            throw new IllegalUserRegistrationException("Could not save user in database");
        }

        return user;
    }

    public UserDTO CheckUserAndRole(String email, String password) {
        return null;
    }

    private static String MD5(String s) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(s.getBytes(),0,s.length());     
        return new BigInteger(1,m.digest()).toString(16); 
    }
}
