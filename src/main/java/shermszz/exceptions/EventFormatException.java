package shermszz.exceptions;

public class EventFormatException extends ShermszzException {
    public EventFormatException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Event Format Error: " + super.getMessage();
    }
}
