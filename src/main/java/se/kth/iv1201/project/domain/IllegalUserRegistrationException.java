package se.kth.iv1201.project.domain;

public class IllegalUserRegistrationException extends Exception {
    
    public IllegalUserRegistrationException(String msg){
        super(msg);
    }
}
