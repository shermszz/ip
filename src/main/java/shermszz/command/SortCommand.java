package shermszz.command;

import shermszz.exceptions.FileSaveException;
import shermszz.storage.Storage;
import shermszz.task.Task;
import shermszz.task.TaskList;
import shermszz.ui.Ui;

/**
 * Sorts the tasks in the task list chronologically.
 * Tasks with dates (Deadlines, Events) are ordered by their associated dates.
 * Tasks without dates (Todos) are placed at the bottom of the list.
 */
public class SortCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sort(new Task.DateComparator());
        try {
            storage.save(tasks);
            return "I've sorted your tasks by date: \n" + ui.listTasks(tasks);
        } catch (FileSaveException e) {
            return "Error saving sorted tasks: " + e.getMessage();
        }
    }

}
