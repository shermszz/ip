public class DeleteFormatException extends ShermszzException {
    public DeleteFormatException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Delete Format Exception: " + super.getMessage();
    }
}
