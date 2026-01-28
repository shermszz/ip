package shermszz.exceptions;

public class ScheduleFormatException extends ShermszzException {

    public ScheduleFormatException(String msg) {
        super(msg);
    }
    @Override
    public String getMessage() {
        return "Schedule Format Error: " + super.getMessage();
    }
}
