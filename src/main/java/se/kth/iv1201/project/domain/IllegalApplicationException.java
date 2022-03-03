package se.kth.iv1201.project.domain;

/**
 * Thrown whenever an attempt is made to perform a transaction that is not
 * allowed by the recruitment application.
 */
public class IllegalApplicationException extends Exception {
    
    /**
     * Creates an instance of the specified error message
     * @param msg explaining message of the error
     */
    public IllegalApplicationException(String msg){
        super(msg);
    }
}
