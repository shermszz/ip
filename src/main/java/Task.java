import java.util.ArrayList;
import java.time.LocalDate;

public class Task {
    private boolean completed;
    private String description;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + this.description;
        }
        return "[] " + this.description;
    }

    public String getStatusIcon() {
        return completed ? "X" : " ";
    }

    public boolean isDone() {
        return this.completed;
    }

    public void markAsDone() {
        this.completed = true;
    }

    public void markAsIncomplete() {
        this.completed = false;
    }

    public boolean isOccurringOn(LocalDate date) { return false; } //Default is to return false, since a general task may or may not have a deadline

    public String toFileFormat() {
        //1 for done, 0 for not done
        return String.format("%d | %s", this.completed ? 1 : 0, this.description);
    }
}
