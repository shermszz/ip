package spongebob.command;

import java.time.LocalDate;

import spongebob.storage.Storage;
import spongebob.task.Task;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Finds and lists all tasks occurring on a specific date.
 */
public class ViewScheduleCommand extends Command {
    private final LocalDate targetDate;

    /**
     * Creates a ViewScheduleCommand for the specified date.
     *
     * @param targetDate The date to filter tasks by.
     */
    public ViewScheduleCommand(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (int i = 0; i < tasks.getSize(); i++) {
            Task t = tasks.get(i);
            if (t.isOccurringOnOrAfter(targetDate)) {
                result.append(t.toString()).append("\n");
                count++;
            }
        }

        if (count == 0) {
            return ui.showNoTaskOnDate(targetDate);
        }
        return "Here are the tasks upcoming for " + targetDate + ":\n" + result.toString();
    }
}
