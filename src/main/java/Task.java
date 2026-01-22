import java.util.ArrayList;

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
}
