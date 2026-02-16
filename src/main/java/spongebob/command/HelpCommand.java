package spongebob.command;

import spongebob.storage.Storage;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Displays the help message containing all supported commands
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
