package shermszz.task;

import java.time.LocalDate; //YYYY-MM-DD
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dueBy; //Expect YYYY-MM-DD format

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = LocalDate.parse(dueBy);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + this.dueBy;
    }

    @Override
    public boolean isOccurringOn(LocalDate date) {
        return this.dueBy.isAfter(date);
    }
}
