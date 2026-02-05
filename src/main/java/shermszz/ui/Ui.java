package shermszz.ui;

import java.time.LocalDate;

import shermszz.task.Task;
import shermszz.task.TaskList;


/**
 * Handles all interactions with the user, including reading input and printing messages.
 */
public class Ui {
    /**
     * Displays the welcome message and logo to the user upon startup.
     */
    public String showWelcome() {
        return "Hello from under the water! I am Spongebob here to record your tasks in my pineapple.\n"
                + "Right now, I can record Todo, Deadline and Event tasks, "
                + "mark your tasks as complete or unmark them as incomplete, "
                + "List the tasks you have recorded so far,"
                + " as well as Delete a task from the record.";
    }

    /**
     * Returns the exit message when the application terminates.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
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
     * Prints the details of a specific task to the console.
     *
     * @param t The task to be displayed.
     */
    public String showTask(Task t) {
        return t.toString();
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
     * Prints the list of matching tasks to the console.
     *
     * @param tasks The TaskList containing the matching tasks.
     */
    public String showFoundTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "No matching tasks found.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list: \n");
            for (int i = 0; i < tasks.getSize(); i++) {
                sb.append(((i + 1) + ". " + tasks.get(i).toString()) + "\n");
            }
            return sb.toString();
        }
    }
}

