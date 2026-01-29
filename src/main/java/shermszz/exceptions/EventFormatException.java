package shermszz.exceptions;

/**
 * Signals that the user input for an Event command does not comply with the expected format.
 * Thrown when the "/from" or "/to" delimiters are missing, or if the start time is placed after the end time.
 */
public class EventFormatException extends ShermszzException {

    /**
     * Constructs a new EventFormatException with the specified error message.
     *
     * @param message The error message explaining the missing or invalid event details.
     */
    public EventFormatException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Event Format Error: " + super.getMessage();
    }
}
