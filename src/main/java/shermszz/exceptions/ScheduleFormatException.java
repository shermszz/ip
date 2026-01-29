package shermszz.exceptions;

/**
 * Signals that the user input for the Schedule command is invalid.
 * Thrown when the date argument is missing or cannot be parsed into the standard YYYY-MM-DD format.
 */
public class ScheduleFormatException extends ShermszzException {

    /**
     * Constructs a new ScheduleFormatException with the specified error message.
     *
     * @param message The error message explaining the invalid date format or missing argument.
     */
    public ScheduleFormatException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Schedule Format Error: " + super.getMessage();
    }
}
