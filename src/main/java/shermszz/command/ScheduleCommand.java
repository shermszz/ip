package shermszz.command;

import java.time.LocalDate;

import shermszz.storage.Storage;
import shermszz.task.Task;
import shermszz.task.TaskList;
import shermszz.ui.Ui;

/**
 * Finds and lists all tasks occurring on a specific date.
 */
public class ScheduleCommand extends Command {
    private final LocalDate targetDate;

    /**
     * Creates a ScheduleCommand for the specified date.
     *
     * @param targetDate The date to filter tasks by.
     */
    public ScheduleCommand(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (int i = 0; i < tasks.getSize(); i++) {
            Task t = tasks.get(i);
            if (t.isOccurringOn(targetDate)) {
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
