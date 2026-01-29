package shermszz.exceptions;

/**
 * Signals that the user input for a Deadline command does not follow the required format.
 * Thrown when the "/by" delimiter or the date is missing/invalid.
 */
public class DeadlineFormatException extends ShermszzException {

    /**
     * Constructs a new DeadlineFormatException with the specified error message.
     *
     * @param message The error message explaining the missing delimiter or invalid arguments.
     */
    public DeadlineFormatException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Deadline Format Error: " + super.getMessage();
    }
}
