package shermszz.exceptions;

/**
 * Signals that the command entered by the user is not recognized by the application.
 * Thrown when the first word of the input string does not match any valid {@code Command} enum.
 */
public class UnknownCommandException extends ShermszzException {

    /**
     * Constructs a new UnknownCommandException with the specified error message.
     *
     * @param message The error message indicating that the command entered is not recognized.
     */
    public UnknownCommandException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Unknown Command Error: " + super.getMessage();
    }
}
