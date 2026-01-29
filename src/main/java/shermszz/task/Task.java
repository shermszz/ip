package shermszz.task;

import java.time.LocalDate;

/**
 * Represents a generic task in the Shermszz application.
 * <p>
 * This abstract class serves as the parent for specific task types (Todo, Deadline, Event).
 * It encapsulates the common behaviors such as description storage and completion status management.
 */
public class Task {
    private boolean completed;
    private String description;

    /**
     * Constructs a new Task with the specified description.
     * Initializes the completion status to false (incomplete).
     *
     * @param description The description of the task.
     */
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

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return completed ? "X" : " ";
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return this.completed;
    }

    /**
     * Marks the task as completed.
     * Updates the internal status to true.
     */
    public void markAsDone() {
        this.completed = true;
    }

    /**
     * Marks the task as incomplete.
     * Updates the internal status to false.
     */
    public void markAsIncomplete() {
        this.completed = false;
    }

    /**
     * Checks if the task is scheduled to occur on the specified date.
     * <p>
     * Default implementation returns false; subclasses should override this
     * if they are associated with specific dates.
     *
     * @param date The date to check against.
     * @return true if the task occurs on the given date, false otherwise.
     */
    public boolean isOccurringOn(LocalDate date) {
        return false;
    }

    /**
     * Formats the task data for storage in the hard disk.
     *
     * @return A string formatted for file storage (e.g., "T | 1 | description").
     */
    public String toFileFormat() {
        //1 for done, 0 for not done
        return String.format("%d | %s", this.completed ? 1 : 0, this.description);
    }
}
