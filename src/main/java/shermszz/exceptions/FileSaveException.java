package shermszz.exceptions;

/**
 * Signals that a critical error occurred while attempting to save task data to the hard disk.
 * Thrown when the storage directory cannot be created or the file cannot be written to.
 */
public class FileSaveException extends ShermszzException {

    /**
     * Constructs a new FileSaveException with the specified error message.
     *
     * @param message The error message explaining why the save operation failed.
     */
    public FileSaveException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
