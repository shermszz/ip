package shermszz.command;

import shermszz.storage.Storage;
import shermszz.task.TaskList;
import shermszz.ui.Ui;

/**
 * Displays the help message containing all supported commands
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
