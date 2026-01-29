package shermszz.exceptions;

/**
 * Represents the base exception for all logic and runtime errors specific to the Shermszz application.
 * All other custom exceptions in this application should inherit from this class.
 */
public class ShermszzException extends Exception {
    /**
     * The detailed error message associated with this exception.
     */
    private String errorMessage;

    /**
     * Constructs a new ShermszzException with the specified detail message.
     *
     * @param errorMessage The error message explaining the cause of the exception.
     */
    public ShermszzException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return this.errorMessage;
    }
}
