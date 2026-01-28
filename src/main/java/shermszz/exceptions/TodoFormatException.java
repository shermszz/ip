package shermszz.exceptions;

public class TodoFormatException extends ShermszzException {

    public TodoFormatException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "Todo Format Error: " + super.getMessage();
    }
}
