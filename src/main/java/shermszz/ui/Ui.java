package shermszz.ui;
import shermszz.task.Task;
import shermszz.task.TaskList;
import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello from under the water! I am Spongebob here to record your tasks in my pineapple.");
        System.out.println("Right now, I can record shermszz.task.Todo, shermszz.task.Deadline and shermszz.task.Event tasks, mark your tasks as complete or unmark them as incomplete, List the tasks you have recorded so far, as well as Delete a task from the record.");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println("--------------------------------------");
    }

    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showError(String message) {
        System.out.println(message);
        showLine();
    }

    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public void showMarked(Task t, String message) {
        System.out.println(message);
        System.out.println(t.toString());
    }

    public void showUnmarked(Task t, String message) {
        System.out.println(message);
        System.out.println(t.toString());
    }

    public void showTaskAdded(Task t, int size) {
        System.out.println("Got it. I've added this task:\n" + t.toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void showDeletedTask(Task toDelete, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(toDelete.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTask(Task t) {
        System.out.println(t.toString());
    }

    public void showNoTaskOnDate(LocalDate date) {
        System.out.println("[You have no tasks occurring on " + date);
    }
}
