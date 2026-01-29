package shermszz.exceptions;

/**
 * Signals that the user input for a Todo command does not follow the required format.
 * Thrown when the description of the todo task is empty or missing.
 */
public class TodoFormatException extends ShermszzException {

    /**
     * Constructs a new TodoFormatException with the specified error message.
     *
     * @param message The error message explaining why the todo format is invalid.
     */
    public TodoFormatException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Todo Format Error: " + super.getMessage();
    }
}
