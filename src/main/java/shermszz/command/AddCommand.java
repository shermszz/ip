package shermszz.command;

import shermszz.exceptions.FileSaveException;
import shermszz.storage.Storage;
import shermszz.task.Task;
import shermszz.task.TaskList;
import shermszz.ui.Ui;

/**
 * Adds a new task to the task list.
 * This command handles Todo, Deadline, and Event tasks polymorphically.
 */
public class AddCommand extends Command {
    private final Task taskToAdd;

    /**
     * Creates an AddCommand with the specified task.
     *
     * @param task The task to be added to the list.
     */
    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert this.taskToAdd != null : "Task to add cannot be null";
        tasks.add(taskToAdd);
        try {
            storage.save(tasks); // Save immediately after adding
            return ui.showTaskAdded(taskToAdd, tasks.getSize());
        } catch (FileSaveException e) {
            return "Error saving task: " + e.getMessage();
        }
    }
}
