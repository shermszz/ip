package shermszz.exceptions;

/**
 * Signals that the user input for a Mark or Unmark command is invalid.
 * Thrown when the target task index is missing, not a valid integer, or refers to a non-existent task.
 */
public class MarkFormatException extends ShermszzException {

    /**
     * Constructs a new MarkFormatException with the specified error message.
     *
     * @param message The error message explaining why the mark/unmark command is invalid.
     */
    public MarkFormatException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Mark Format Error: " + super.getMessage();
    }
}
