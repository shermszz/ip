package shermszz.command;

import shermszz.storage.Storage;
import shermszz.task.TaskList;
import shermszz.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command and returns the result.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface to generate responses.
     * @param storage The storage to save changes.
     * @return The response string to display to the user (GUI-ready).
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if this command should exit the application.
     */
    public boolean isExit() {
        return false;
    }
}

