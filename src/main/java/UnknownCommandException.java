public class UnknownCommandException extends ShermszzException {
    public UnknownCommandException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Unknown Command Error: " + super.getMessage();
    }
}
