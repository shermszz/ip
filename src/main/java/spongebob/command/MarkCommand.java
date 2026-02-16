package spongebob.command;

import spongebob.exceptions.FileSaveException;
import spongebob.storage.Storage;
import spongebob.task.Task;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Marks a specific task as completed.
 */

public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a MarkCommand for the specified task index.
     *
     * @param index The zero-based index of the task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = tasks.get(index);
            if (t.isDone()) {
                return ui.showMarked(t, "This task is already marked as completed.");
            }
            t.markAsDone();
            storage.save(tasks);
            return ui.showMarked(t, "Nice! I've marked this task as done: ");
        } catch (IndexOutOfBoundsException e) {
            return "Error: Invalid task index.";
        } catch (FileSaveException e) {
            return "Error saving mark status: " + e.getMessage();
        }
    }
}
