package shermszz.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start); //Start and end should be in YYYY-MM-DD format
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + this.start + " | " + this.end;
    }

    @Override
    public boolean isOccurringOn(LocalDate date) {
        return this.start.isBefore(date) && this.end.isAfter(date);
    }
}
