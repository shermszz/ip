package shermszz.command;

import shermszz.exceptions.FileSaveException;
import shermszz.storage.Storage;
import shermszz.task.Task;
import shermszz.task.TaskList;
import shermszz.ui.Ui;

/**
 * Marks a specific task as incomplete.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates an UnmarkCommand for the specified task index.
     *
     * @param index The zero-based index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = tasks.get(index);
            if (!t.isDone()) {
                return ui.showUnmarked(t, "This task is already incomplete.");
            }
            t.markAsIncomplete();
            storage.save(tasks);
            return ui.showUnmarked(t, "OK, I've marked this task as not done yet:");
        } catch (IndexOutOfBoundsException e) {
            return "Error: Invalid task index.";
        } catch (FileSaveException e) {
            return "Error saving unmark status: " + e.getMessage();
        }
    }
}
