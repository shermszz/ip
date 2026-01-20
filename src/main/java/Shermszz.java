import java.util.ArrayList;
import java.util.Scanner;

public class Shermszz {
    private static ArrayList<Task> list; // To store a list of user's tasks.

    private static void printLine() {
        System.out.println("--------------------------------------");
    }

    private static void greetUser() {
        printLine();
        System.out.println("Hello! I'm Shermszz");
        System.out.println("What can I do for you?\n");
        printLine();
    }

    private static void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printLine();
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }

    private static void markTask(String command) {
        try {
            String[] parts = command.split(" "); //Should be mark X, where X is a number
            int idx = Integer.parseInt(parts[1]);
            if (idx > list.size() || idx < 1) {
                System.out.println("Cannot mark task with ID = " + idx + ". It does not exist.");
            } else {
                //Within range, so we mark the task
                Task t = list.get(idx - 1); //0 -based indexing
                if (t.getStatusIcon().equals("X")) {
                    System.out.println("This task is already marked as completed.");
                } else {
                    t.markAsDone(); //Set to completed
                    System.out.println("Nice! I've marked this task as done:");
                }
                System.out.println(t.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("You did no enter an appropriate format. Please enter 'mark X', where X is a digit.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You did no enter an appropriate format. Please enter 'mark X', where X is a digit.");
        }


    }

    private static void unmarkTask(String command) {
        try {
            String[] parts = command.split(" "); //Should be mark X, where X is a number
            int idx = Integer.parseInt(parts[1]);
            if (idx > list.size() || idx < 1) {
                System.out.println("Cannot unmark task with ID =  " + idx + ". It does not exist.");
            } else {
                //Within range, so we mark the task
                Task t = list.get(idx - 1); //0 -based indexing
                if (t.getStatusIcon().equals(" ")) {
                    //Already an unmarked task, inform the user
                    System.out.println("This task has yet to be completed.");
                } else {
                    t.markAsIncomplete(); //Set to incomplete now
                    System.out.println("Ok, I've marked this task as not done yet:");
                }
                System.out.println(t.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("You did no enter an appropriate format. Please enter 'unmark X', where X is a digit.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You did no enter an appropriate format. Please enter 'unmark X', where X is a digit.");
        }

    }

    private static void addTask(String command) {
        printLine();
        Task t = new Task(command);
        list.add(t);
        System.out.println("Shermszz added your instruction '"  + command + "' to your list of tasks");
        printLine();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<>();
        greetUser();

        while (true) {
            String instruction = sc.nextLine();
            if (instruction.equals("bye")) break;
            else if (instruction.equals("list")) {
                listTasks();
            } else if (instruction.startsWith("mark")) { // Command to mark some instruction
                markTask(instruction);
            } else if (instruction.startsWith("unmark")) {
                unmarkTask(instruction);
            }
            else {
                //Default adding instructions
                addTask(instruction);
            }
        }

        sayBye();
        sc.close();
    }
}
