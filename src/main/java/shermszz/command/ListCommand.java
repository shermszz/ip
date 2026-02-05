package shermszz.command;

import shermszz.storage.Storage;
import shermszz.task.TaskList;
import shermszz.ui.Ui;

/**
 * Lists all tasks currently stored in the application.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listTasks(tasks); // Ensure Ui.listTasks returns the full list string
    }
}
