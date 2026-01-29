package shermszz.ui;
import shermszz.task.Task;
import shermszz.task.TaskList;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Handles all interactions with the user, including reading input and printing messages.
 */
public class Ui {
    private Scanner sc;

    /**
     * Initializes the UI and sets up the scanner for user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next command entered by the user.
     *
     * @return The full command string entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the welcome message and logo to the user upon startup.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello from under the water! I am Spongebob here to record your tasks in my pineapple.");
        System.out.println("Right now, I can record Todo, Deadline and Event tasks, mark your tasks as complete or unmark them as incomplete, List the tasks you have recorded so far, as well as Delete a task from the record.");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Prints a horizontal line separator to the console.
     * Used to visually separate command outputs for better readability.
     */
    public void showLine() {
        System.out.println("--------------------------------------");
    }

    /**
     * Displays the exit message when the application terminates.
     */
    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints an error message to the console.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
        showLine();
    }

    /**
     * Prints the list of tasks to the console.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Displays a message confirming a task has been marked.
     *
     * @param t       The task to mark.
     * @param message The message to inform the user the task has been marked.
     */
    public void showMarked(Task t, String message) {
        System.out.println(message);
        System.out.println(t.toString());
    }

    /**
     * Displays a message confirming a task has been unmarked.
     *
     * @param t       The task to unmark.
     * @param message The message to inform the user the task has been unmarked.
     */
    public void showUnmarked(Task t, String message) {
        System.out.println(message);
        System.out.println(t.toString());
    }

    /**
     * Displays a message confirming a task has been added.
     *
     * @param t    The task that was added.
     * @param size The new size of the task list.
     */
    public void showTaskAdded(Task t, int size) {
        System.out.println("Got it. I've added this task:\n" + t.toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    /**
     * Displays a message confirming a task has been deleted.
     *
     * @param toDelete The task to delete.
     * @param size     The new size of the task list.
     */
    public void showDeletedTask(Task toDelete, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(toDelete.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the details of a specific task to the console.
     *
     * @param t The task to be displayed.
     */
    public void showTask(Task t) {
        System.out.println(t.toString());
    }

    /**
     * Displays a message indicating that no tasks occur on the specified date.
     *
     * @param date The date checked by the user.
     */
    public void showNoTaskOnDate(LocalDate date) {
        System.out.println("[You have no tasks occurring on " + date);
    }
}
