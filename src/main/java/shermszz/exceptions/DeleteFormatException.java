package shermszz.exceptions;

/**
 * Signals that the user input for a Delete command is invalid.
 * Thrown when the task index argument is missing, not a number, or out of the bounds of the task list.
 */
public class DeleteFormatException extends ShermszzException {

    /**
     * Constructs a new DeleteFormatException with the specified error message.
     *
     * @param message The error message explaining why the delete command is invalid.
     */
    public DeleteFormatException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Delete Format Exception: " + super.getMessage();
    }
}
