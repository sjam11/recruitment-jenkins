package se.kth.iv1201.project.application;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.project.domain.App;
import se.kth.iv1201.project.domain.Availability;
import se.kth.iv1201.project.domain.Competence;
import se.kth.iv1201.project.domain.CompetenceProfile;
import se.kth.iv1201.project.domain.IllegalApplicationException;
import se.kth.iv1201.project.domain.Role;
import se.kth.iv1201.project.domain.User;
import se.kth.iv1201.project.domain.UserDTO;
import se.kth.iv1201.project.repository.UserRepository;
import se.kth.iv1201.project.repository.AvailabilityRepository;
import se.kth.iv1201.project.repository.CompetenceProfileRepository;
import se.kth.iv1201.project.repository.CompetenceRepository;
import se.kth.iv1201.project.repository.RoleRepository;

/**
 * This is the Recruitment application class.
 * 
 * Transaction demarcation is defined by methods in this class, a transaction
 * starts when a method is called from the presentation layer, and ends (commit
 * or rollback) when that method returns.
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class ApplicationService {

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

    /**
     * Returns a user object, for the user specified with user id
     * 
     * @param id of the user to fetch from the db
     * @return user object
     * @throws IllegalApplicationException if fetching user fails
     */
    public UserDTO findUserByUserId(int id) throws IllegalApplicationException {
        try {
            return userRepository.findPersonByPersonID(id);
        } catch (Exception e) {
            throw new IllegalApplicationException("Server error.");
        }
    }

    /**
     * Creates a user with the specified information.
     * 
     * @param firstName users firstname.
     * @param lastName  users lastname.
     * @param pin       users personal identification number.
     * @param email     users email.
     * @param password  users password.
     * @param roleName  users rol.
     * @param username  users username.
     * @return the created object user.
     * @throws IllegalApplicationException if failed to create user
     */
    public UserDTO createUser(String firstName, String lastName, String pin, String email,
            String password, String roleName, String username) throws IllegalApplicationException {

        if (email == null || firstName == null || lastName == null || pin == null || password == null
                || roleName == null || username == null) {
            throw new IllegalApplicationException("Fields left empty, please fill out every field.");
        }

        Role role = roleRepository.findRoleByName(roleName);

        if (userRepository.findPersonByPin(pin) != null) {
            throw new IllegalApplicationException("Person identification number: " +
                    pin + " already exist.");
        }

        if (userRepository.findPersonByUsername(username) != null) {
            throw new IllegalApplicationException("Username: " +
                    username + " is already in use.");
        }

        if (role.getName() == null) {
            throw new IllegalApplicationException("No matching role for: " +
                    roleName + "in the system.");
        }

        User user = new User(firstName, lastName, pin, email, MD5(password), role.getRoleID(), username);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalApplicationException("Could not save user in database");
        }

        return user;
    }

    /**
     * Checks if the user is a register user.
     * 
     * @param email    the email to check.
     * @param password the password to check.
     * @return the user if its registered.
     * @throws IllegalApplicationException if failed to check user
     */
    public UserDTO checkUser(String username, String password) throws IllegalApplicationException {
        if (username == null || password == null) {
            throw new IllegalApplicationException("Fields left empty, please fill out every field.");
        }

        User user = userRepository.findPersonByUsername(username);
        if (user == null) {
            throw new IllegalApplicationException("The username: " +
                    username + "does not have a account.");
        }

        if (!MD5(password).equals(user.getPassword())) {
            throw new IllegalApplicationException("Incorrect password.");
        }

        return user;
    }

    /**
     * Checks what role the current user has
     * 
     * @param user to be checked
     * @return the role of the user
     * @throws IllegalApplicationException if failed to check role for user
     */
    public String checkRole(User user) throws IllegalApplicationException {
        if (user == null) {
            throw new IllegalApplicationException("Server error.");
        }

        return roleRepository.findRoleNameByRoleID(user.getRoleID());
    }

    /**
     * Creates a competence profile for the specified user with the specified
     * information.
     * 
     * @param user              the user
     * @param competenceName    the name of the competence
     * @param yearsOfExperience the years of experience.
     * @return the comptence profile
     */
    public void addCompetence(UserDTO user, String competenceName, BigDecimal yearsOfExperience)
            throws IllegalApplicationException {
        if (user == null || competenceName == null || yearsOfExperience == null) {
            throw new IllegalApplicationException("Server error.");
        }

        competenceName = competenceName.replaceAll("-", " ");
        Competence competence = competenceRepository.findCompetenceByName(competenceName);
        int personID = user.getPersonID();
        CompetenceProfile competenceProfile = new CompetenceProfile(personID, competence.getCompetenceID(),
                yearsOfExperience);

        try {
            competenceProfileRepository.save(competenceProfile);
        } catch (Exception e) {
            throw new IllegalApplicationException("Could not save competence in database");
        }
    }

    /**
     * Creates a availibility period the user.
     * 
     * @param user     the user.
     * @param fromDate available from date.
     * @param toDate   available to date.
     * @throws IllegalApplicationException if failed to create user
     */
    public void addAvailability(UserDTO user, Date fromDate, Date toDate) throws IllegalApplicationException {
        if (user == null || fromDate == null || toDate == null) {
            throw new IllegalApplicationException("Server error.");
        }

        if (fromDate.compareTo(toDate) > 0) {
            throw new IllegalApplicationException("To date is before from date");
        }
        int personID = user.getPersonID();
        Availability availability = new Availability(personID, fromDate, toDate);

        try {
            availabilityRepository.save(availability);
        } catch (Exception e) {
            throw new IllegalApplicationException("Could not save availibility in database");
        }
    }

    /**
     * Fetches all applications from the database
     * 
     * @return all of the availability period and competence for all applicants.
     * @throws IllegalApplicationException if failed to fetch all applications from
     *                                     db
     */
    public ArrayList<App> getApplications() throws IllegalApplicationException {
        ArrayList<App> applications = new ArrayList<App>();
        App app;
        HashMap<Date, Date> availability = new HashMap<>();
        HashMap<String, BigDecimal> competence = new HashMap<>();

        try {
            List<User> allUsers = userRepository.findAllByRoleID(2);

            for (User user : allUsers) {
                int userID = user.getPersonID();
                List<Availability> allAvailability = availabilityRepository.findAllByPersonID(userID);
                List<CompetenceProfile> allCompetence = competenceProfileRepository.findAllByPersonID(userID);
                for (Availability a : allAvailability) {
                    availability.put(a.getFromDate(), a.getToDate());
                }

                for (CompetenceProfile c : allCompetence) {
                    String competenceName = competenceRepository.getCompetenceNameByCompetenceID(c.getCompetenceID());
                    competence.put(competenceName, c.getYearOfExperience());
                }

                app = new App(user, competence, availability);
                applications.add(app);
            }

            return applications;
        } catch (Exception e) {
            throw new IllegalApplicationException("Database fetch error");
        }
    }

    /**
     * Fetches all applications with the specified competence.
     * 
     * @param competenceName name of the competence we want the applications with
     * @return returns all applications with specified competence name
     * @throws IllegalApplicationException if failed to fetch applications
     */
    public ArrayList<App> getApplicationsWithCompetenceName(String competenceName) throws IllegalApplicationException {
        competenceName = competenceName.replaceAll("-", " ");
        ArrayList<App> applications = new ArrayList<App>();
        App app;
        HashMap<Date, Date> availability = new HashMap<>();
        HashMap<String, BigDecimal> competence = new HashMap<>();

        try {
            int competenceID = competenceRepository.findCompetenceByName(competenceName).getCompetenceID();

            List<CompetenceProfile> allCompetencesWithCompetenceName = competenceProfileRepository
                    .findAllByCompetenceID(competenceID);
            User user;

            for (CompetenceProfile c : allCompetencesWithCompetenceName) {
                competence.clear();
                availability.clear();
                competence.put(competenceName, c.getYearOfExperience());
                user = userRepository.findPersonByPersonID(c.getPersonID());

                List<Availability> allAvailability = availabilityRepository.findAllByPersonID(user.getPersonID());
                for (Availability a : allAvailability) {
                    availability.put(a.getFromDate(), a.getToDate());
                }

                app = new App(user, competence, availability);
                applications.add(app);
            }
            return applications;
        } catch (Exception e) {
            throw new IllegalApplicationException("Database fetch error");
        }
    }

    
    public ArrayList<App> getApplic(String personID) throws IllegalApplicationException {
        App application;
        ArrayList<App> applications = new ArrayList<App>();
        int person_id = Integer.parseInt(personID);
        HashMap<Date, Date> availability = new HashMap<>();
        HashMap<String, BigDecimal> competence = new HashMap<>();
        try {
            User user = userRepository.findPersonByPersonID(person_id);
            List<CompetenceProfile> allCompetencesForPerson = competenceProfileRepository
                    .findAllByPersonID(person_id);

            for (CompetenceProfile c : allCompetencesForPerson) {
                competence.clear();
                availability.clear();
                competence.put(competenceRepository.getCompetenceNameByCompetenceID(c.getCompetenceID()), c.getYearOfExperience());

                List<Availability> allAvailability = availabilityRepository.findAllByPersonID(person_id);
                for (Availability a : allAvailability) {
                    availability.put(a.getFromDate(), a.getToDate());
                }

                application = new App(user, competence, availability);
                applications.add(application);
            }
            return applications;
        } catch(Exception e) {
            throw new IllegalApplicationException("Database fetch error");
        }
    }

    /**
     * MD5 Hashing algorith used for hashing passwords.
     * 
     * @param s password
     * @return hashed password
     */
    private static String MD5(String s) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(s.getBytes(), 0, s.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}
