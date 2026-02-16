package spongebob.command;

import spongebob.storage.Storage;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Terminates the application session.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye(); // Ensure Ui.showBye() returns the goodbye string
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

