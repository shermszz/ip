package shermszz;
import shermszz.parser.Parser;
import shermszz.storage.Storage;
import shermszz.task.Task;
import shermszz.task.Todo;
import shermszz.task.Deadline;
import shermszz.task.Event;
import shermszz.task.TaskList;
import shermszz.ui.Ui;
import shermszz.parser.Command;
import shermszz.exceptions.ShermszzException;
import shermszz.exceptions.FileSaveException;
import shermszz.exceptions.ScheduleFormatException;
import shermszz.exceptions.MarkFormatException;
import shermszz.exceptions.TodoFormatException;
import shermszz.exceptions.DeadlineFormatException;
import shermszz.exceptions.EventFormatException;
import shermszz.exceptions.DeleteFormatException;


import java.time.format.DateTimeParseException;
import java.time.LocalDate;

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
     * Attempts to laod existing data from storage; create a new list if loading fails.
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
     * Runs the main program loop.
     * Continuously reads user commands, parses them, and executes the corresponding logic
     * until the exit command is issued
     */
    public void run() { //run the chatbot
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                //Convert the string into an enum
                String instruction = ui.readCommand();
                Command c = Parser.parse(instruction);
                switch (c) {
                    case BYE:
                        isExit = true;
                        ui.showBye();
                        break;
                    case LIST:
                        listTasks();
                        break;
                    case SCHEDULE:
                        printTasksOnDate(instruction);
                        break;
                    case MARK:
                        markTask(instruction);
                        saveTasks();
                        break;
                    case UNMARK:
                        unmarkTask(instruction);
                        saveTasks();
                        break;
                    case DELETE:
                        deleteTask(instruction);
                        saveTasks();
                        break;
                    case TODO:
                        addTodoTask(instruction);
                        saveTasks();
                        break;
                    case DEADLINE:
                        addDeadlineTask(instruction);
                        saveTasks();
                        break;
                    case EVENT:
                        addEventTask(instruction);
                        saveTasks();
                        break;
                }
            } catch (ShermszzException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private void saveTasks() {
        try {
            this.storage.save(this.tasks);
        } catch (FileSaveException e) {
            this.ui.showError(e.getMessage());
        }
    }

    private void listTasks() {
        this.ui.listTasks(this.tasks);
        this.ui.showLine();
    }

    private void printTasksOnDate(String command) {
        try {
            LocalDate targetDate = Parser.parseSchedule(command); //shermszz.parser.Parser returns the date
            int count = 0;
            for (int i = 0; i < this.tasks.getSize(); i++) {
                Task t = this.tasks.get(i);
                if (t.isOccurringOn(targetDate)) {
                    this.ui.showTask(t);
                    count++;
                }
            }
            if (count == 0) {
                this.ui.showNoTaskOnDate(targetDate);
            }
            this.ui.showLine();
        } catch (ScheduleFormatException e) {
            this.ui.showError(e.getMessage());
        }
    }

    private void markTask(String command) {
        try {
            int idx = Parser.parseMarking(command, this.tasks.getSize()); // Will retrieve the id of the task we want to mark as done
            //Within range, so we mark the task
            Task t = this.tasks.get(idx - 1); //0 -based indexing
            if (t.isDone()) {
                this.ui.showMarked(t, "This task is already marked as completed.");
            } else {
                t.markAsDone();
                this.ui.showMarked(t, "Nice! I've marked this task as done:");
            }
            this.ui.showLine();
        } catch (MarkFormatException e) {
            this.ui.showError(e.getMessage());
        }
    }

    private void unmarkTask(String command) {
        try {
            int idx = Parser.parseUnmarking(command, this.tasks.getSize()); //Will retrieve the id of the task we want to unmark
            //Within range, so we mark the task
            Task t = this.tasks.get(idx - 1); //0 -based indexing
            if (!t.isDone()) {
                this.ui.showUnmarked(t, "This task has yet to be completed.");
            } else {
                t.markAsIncomplete();
                this.ui.showUnmarked(t, "Ok, I've unmarked this task to be not done yet:");
            }
            this.ui.showLine();
        } catch (MarkFormatException e) {
            this.ui.showError(e.getMessage());
        }
    }

    private void addTodoTask(String command) {
        try {
            String description = Parser.parseTodo(command);
            Task t = new Todo(description);
            this.tasks.add(t);
            this.ui.showTaskAdded(t, this.tasks.getSize());
            this.ui.showLine();
        } catch (TodoFormatException e) {
            this.ui.showError(e.getMessage());
        }
    }

    private void addDeadlineTask(String command) {
        try {
            String[] deadlineParts = Parser.parseDeadline(command); //[ description, dueBy ]
            String description = deadlineParts[0];
            String dueBy = deadlineParts[1];
            Task t = new Deadline(description, dueBy);
            this.tasks.add(t);
            this.ui.showTaskAdded(t, this.tasks.getSize());
            this.ui.showLine();
        } catch (DateTimeParseException e) {
            this.ui.showError("Invalid Date Format. Please use YYYY-MM-DD (e.g. 2025-01-28 to represent 28th Jan 2025) to represent the deadline date.");
        } catch (DeadlineFormatException e) {
            this.ui.showError(e.getMessage());
        }
    }

    private void addEventTask(String command) throws EventFormatException {
        try {
            String[] eventParts = Parser.parseEvent(command); //[ description, /from date, /to date ]
            String description = eventParts[0];
            String start = eventParts[1];
            String end = eventParts[2];
            Task t = new Event(description, start, end);
            this.tasks.add(t);
            this.ui.showTaskAdded(t, this.tasks.getSize());
            this.ui.showLine();
        } catch (DateTimeParseException e) {
            this.ui.showError("Invalid Date Format. Please use YYYY-MM-DD (e.g. 2025-01-28 to represent 28th Jan 2025) to represent the 2 dates required for an shermszz.task.Event task.");
        }
    }


    private void deleteTask(String command) {
        try {
            int id = Parser.parseDeletion(command, this.tasks.getSize());
            Task toDelete = this.tasks.remove(id - 1);
            this.ui.showDeletedTask(toDelete, this.tasks.getSize());
        } catch (DeleteFormatException e) {
            this.ui.showError(e.getMessage());
        }
    }

    /**
     * The main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
       new Shermszz("data/shermszz.txt").run();
    }
}
