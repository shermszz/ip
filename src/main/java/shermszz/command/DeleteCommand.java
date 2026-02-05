package shermszz.command;

import shermszz.exceptions.FileSaveException;
import shermszz.storage.Storage;
import shermszz.task.Task;
import shermszz.task.TaskList;
import shermszz.ui.Ui;

/**
 * Deletes a task from the task list based on its index.
 */
public class DeleteCommand extends Command {
    private final int targetIndex; //0-based index

    /**
     * Creates a DeleteCommand for the specified task index.
     *
     * @param targetIndex The zero-based index of the task to delete.
     */
    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deletedTask = tasks.remove(targetIndex); //Parser passes 0-based index here
            storage.save(tasks);
            return ui.showDeletedTask(deletedTask, tasks.getSize());
        } catch (FileSaveException e) {
            return "Error saving after delete: " + e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Error: Task index out of bounds.";
        }
    }
}
