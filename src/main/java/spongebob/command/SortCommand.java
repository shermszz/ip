package spongebob.command;

import spongebob.exceptions.FileSaveException;
import spongebob.storage.Storage;
import spongebob.task.Task;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

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
