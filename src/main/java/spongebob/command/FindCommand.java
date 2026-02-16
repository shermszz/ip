package spongebob.command;

import spongebob.storage.Storage;
import spongebob.task.Task;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Finds and lists all tasks in the task list whose description contains the argument keyword.
 * Keyword matching is case-sensitive.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            if (task.toString().toLowerCase().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return ui.showFoundTasks(foundTasks);
    }
}
