package se.kth.iv1201.project.application;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.project.domain.CompetenceDTO;
import se.kth.iv1201.project.domain.CompetenceProfile;
import se.kth.iv1201.project.domain.CompetenceProfileDTO;
import se.kth.iv1201.project.domain.IllegalUserRegistrationException;
import se.kth.iv1201.project.domain.User;
import se.kth.iv1201.project.domain.UserDTO;
import se.kth.iv1201.project.repository.UserRepository;
import se.kth.iv1201.project.repository.AvailabilityRepository;
import se.kth.iv1201.project.repository.CompetenceProfileRepository;
import se.kth.iv1201.project.repository.CompetenceRepository;
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
    @Autowired
    private CompetenceRepository competenceRepository;

    public UserDTO findUserByUserId(int id) {
        return userRepository.findPersonByPersonID(id);
    }

    /**
     * Creates a user with the specified information.
     * @param firstName users firstname.
     * @param lastName users lastname.
     * @param pin users personal identification number.
     * @param email users email.
     * @param password users password.
     * @param roleName users rol.
     * @param username users username.
     * @return the created object user.
     * @throws IllegalUserRegistrationException
     */
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

        int roleID = roleRepository.findRoleIDByName(roleName);
        User user = new User(firstName, lastName, pin, email, MD5(password), roleID, username);
        try{
            userRepository.save(user);
        } catch(Exception e){
            throw new IllegalUserRegistrationException("Could not save user in database");
        }

        return user;
    }

    /**
     * Checks if the user is a register user.
     * @param email the email to check.
     * @param password the password to check.
     * @return the user if its registered.
     * @throws IllegalUserRegistrationException
     */
    public UserDTO CheckUser(String username, String password) throws IllegalUserRegistrationException {
        User user = userRepository.findPersonByUsername(username);
        if(user == null){
            throw new IllegalUserRegistrationException("The username: " + 
            username + "does not have a account."); 
        }

        if(user.getPassword() != MD5(password)){
            throw new IllegalUserRegistrationException("Incorrect password.");
        }

        return user;
    }

    /**
     * Checks what role the current user has
     * @param user to be checked
     * @return the role of the user
     * @throws IllegalUserRegistrationException
     */
    public String checkRole(User user) throws IllegalUserRegistrationException{
        if(user == null) {
            throw new IllegalUserRegistrationException("Database error."); 
        }

        String role = roleRepository.findRoleNameByRoleID(user.getRoleID());
        return role;
    }

    /**
     * Creates a competence profile for the specified user with the specified information.
     * @param user the user
     * @param competenceName the name of the competence
     * @param yearsOfExperience the years of experience. 
     * @return the comptence profile
     */
    public void createCompetenceProfile(User user, String competenceName, BigDecimal yearsOfExperience) throws IllegalUserRegistrationException{
        int competenceID = competenceRepository.getCompetenceID(competenceName);
        int personID = user.getPersonID();
        CompetenceProfile competenceProfile = new CompetenceProfile(personID, competenceID, yearsOfExperience);

        try{
            competenceProfileRepository.saveCompetenceProfile(competenceProfile);
        } catch(Exception e){
            throw new IllegalUserRegistrationException("Could not save user in database");
        }
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
