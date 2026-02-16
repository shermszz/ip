package spongebob.ui;

import java.time.LocalDate;

import spongebob.task.Task;
import spongebob.task.TaskList;


/**
 * Handles all interactions with the user, including reading input and printing messages.
 */
public class Ui {
    /**
     * Displays the welcome message and logo to the user upon startup.
     */
    public String showWelcome() {
        return "Hello from under the water! I am Spongebob here to record your tasks in my pineapple.\n"
                + "Right now, I can record Todo, Deadline and Event tasks,\n "
                + "Mark your tasks as complete or Unmark them as incomplete,\n "
                + "List the tasks you have recorded so far,\n "
                + "Delete a task from the record,\n "
                + "List the tasks due by a certain schedule, and also\n "
                + "Sort your tasks based on the next earliest due date.\n"
                + "If you are not sure about anything, type 'help' and I will open my pineapple for you "
                + "to clarify your questions";
    }

    /**
     * Returns the exit message when the application terminates.
     */
    public String showBye() {
        return "See you next time buddy";
    }

    /**
     * Returns an error message as a String.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return message + "\n";
    }

    /**
     * Returns the list of tasks to the console as a String.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public String listTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Displays a message confirming a task has been marked.
     *
     * @param t       The task to mark.
     * @param message The message to inform the user the task has been marked.
     */
    public String showMarked(Task t, String message) {
        return message + "\n" + t.toString();
    }

    /**
     * Displays a message confirming a task has been unmarked.
     *
     * @param t       The task to unmark.
     * @param message The message to inform the user the task has been unmarked.
     */
    public String showUnmarked(Task t, String message) {
        return message + "\n" + t.toString();
    }

    /**
     * Returns a String message confirming a task has been added.
     *
     * @param t    The task that was added.
     * @param size The new size of the task list.
     */
    public String showTaskAdded(Task t, int size) {
        return "Got it. I've added this task:\n" + t.toString() + "\nNow you have " + size + " tasks in the list";
    }

    /**
     * Returns a String message confirming a task has been deleted.
     *
     * @param toDelete The task to delete.
     * @param size     The new size of the task list.
     */
    public String showDeletedTask(Task toDelete, int size) {
        return "Noted. I've removed this task:\n" + toDelete.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating that no tasks occur on the specified date.
     *
     * @param date The date checked by the user.
     */
    public String showNoTaskOnDate(LocalDate date) {
        return "You have no tasks occurring on " + date;
    }

    /**
     * Returns a list of supported commands.
     *
     * @return The formatted help string
     */
    public String showHelp() {
        return "Here are the commands I know:\n"
                + "1. todo <description> --> Add a todo task\n"
                + "2. deadline <description> /by <date> --> Add a task with a deadline\n"
                + "3. event <description> /from <date> /to <date> --> Add an event task with a start and end date\n"
                + "4. list --> Lists all tasks in my record\n"
                + "5. mark <1-based index> --> Mark specified task at the listed index as complete\n"
                + "6. unmark <1-based index> --> Unmark specified task at the listed index as incomplete\n"
                + "7. delete <1-based index> --> Deletes the specified task at the listed index\n"
                + "8. view <date> --> Finds tasks that are going to occur by the specified date \n"
                + "9. sort --> Displays all tasks again, but in non-descending order of due dates\n"
                + "10. bye --> To exit my pineapple";
    }
}

