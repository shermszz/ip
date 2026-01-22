public class DeadlineFormatException extends ShermszzException {
    public DeadlineFormatException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Deadline Format Error: " + super.getMessage();
    }
}
