package shermszz;

import shermszz.command.Command;
import shermszz.exceptions.ShermszzException;
import shermszz.parser.Parser;
import shermszz.storage.Storage;
import shermszz.task.TaskList;
import shermszz.ui.Ui;

/**
 * The main entry point of the Shermszz Chatbot Application.
 * Initializes the UI, Storage and TaskList components and runs the command loop.
 */

public class Shermszz {
    private Storage storage;
    private TaskList tasks;
    private Ui ui; //In charge of user interface, all the print statements are done in the ui object

    /**
     * Initializes the application with the specified file path.
     * Attempts to load existing data from storage; create a new list if loading fails.
     * @param filepath The file path where task data is stored.
     */
    public Shermszz(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (ShermszzException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList(); //If the file is unable to load in the storage, we default an empty list
        }
    }

    /**
     * Returns the welcome message to be displayed when the application starts.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * Generates a response for the user's chat message.
     * This method is called by the GUI (MainWindow).
     */
    public String getResponse(String input) {
        assert input != null : "Input passed to Logic should not be null";
        try {
            // 1. Parse the input string into a Command object
            Command c = Parser.parseCommand(input);

            // 2. Execute the command and capture the returned String
            String response = c.execute(tasks, ui, storage);

            return response;

        } catch (ShermszzException e) {
            // 3. If an error occurs, return the error message as the response
            return e.getMessage();
        }
    }
}
