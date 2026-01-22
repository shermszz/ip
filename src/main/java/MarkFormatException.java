public class MarkFormatException extends ShermszzException {
    public MarkFormatException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Mark Format Error: " + super.getMessage();
    }
}
