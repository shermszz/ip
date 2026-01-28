import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Shermszz {
    private static ArrayList<Task> list; // To store a list of user's tasks.

    private static void saveTasks() {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdirs(); //Create directory if it does not exist
            }

            FileWriter fw = new FileWriter("data/shermszz.txt");
            for (Task t : list) {
                fw.write(t.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        try {
            File f = new File("data/shermszz.txt");
            if (!f.exists()) {
                return; //The file has nothing to load, so we start with an empty list
            }
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                try {
                    parseAndAdd(line);
                } catch (Exception e) {
                    System.out.println("Corrupted data found and skipped: " + line);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private static void parseAndAdd(String line) {
        String[] parts = line.split(" \\| "); //To split the array up based on " | ".
        //E.g. T | 1 | read book --> ["T", "1", "read book"]
        String type = parts[0]; //Either a Todo or Deadline or Event task
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task t = null;
        switch (type) {
            case "T":
                //E.g. T | 0 | test todo
                t = new Todo(description);
                break;
            case "D":
                //E.g. D | 0 | test deadline | 2025-01-01
                t = new Deadline(description, parts[3]);
                break;
            case "E":
                //E.g. E | 0 | test event | 2025-01-01 | 2026-01-01
                t = new Event(description, parts[3], parts[4]);
                break;
        }

        if (t != null) {
            if (isDone) t.markAsDone();
            list.add(t);
        }
    }

    private static void printLine() {
        System.out.println("--------------------------------------");
    }

    private static void greetUser() {
        printLine();
        System.out.println("Hello from under the water! I am Spongebob here to record your tasks in my pineapple.");
        System.out.println("Right now, I can record Todo, Deadline and Event tasks, mark your tasks as complete or unmark them as incomplete, List the tasks you have recorded so far, as well as Delete a task from the record.");
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
        printLine();
    }

    private static void printTasksOnDate(String command) {
        try {
            //Command format: "schedule 2025-01-01" --> Will list all deadline/event tasks that are due by 2025-01-01
            String[] parts = command.split(" ");
            if (parts.length < 2) {
                System.out.println("Please specify a date: schedule YYYY-MM-DD");
                printLine();
                return;
            }
            String dateAsString = parts[1].trim();
            LocalDate targetDate = LocalDate.parse(dateAsString);
            System.out.println("Tasks that will occur on / are occurring on " + targetDate + " are listed below: ");
            int count = 0;
            for (Task t : list) {
                if (t.isOccurringOn(targetDate)) {
                    System.out.println(t.toString());
                    count++;
                }
            }
            if (count == 0) System.out.println("[You have no tasks occurring on " + targetDate);
            printLine();

        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date Format. Please use YYYY-MM-DD.");
            printLine();
        }
    }

    private static void markTask(String command) throws MarkFormatException {
        String[] parts = command.split(" "); //Should be mark X, where X is a number
        if (parts.length < 2) {
            throw new MarkFormatException("You must specify a task to mark after typing \"mark\"");
        }
        try {
            int idx = Integer.parseInt(parts[1]); //Could throw NumberFormatExecption if a digit isnt entered
            if (idx > list.size() || idx < 1) {
                throw new MarkFormatException("Cannot mark task with ID = " + idx + ". It does not exist.");
            } else {
                //Within range, so we mark the task
                Task t = list.get(idx - 1); //0 -based indexing
                if (t.isDone()) {
                    System.out.println("This task is already marked as completed.");
                } else {
                    t.markAsDone(); //Set to completed
                    System.out.println("Nice! I've marked this task as done:");
                }
                System.out.println(t.toString());
            }
        } catch (NumberFormatException e) {
            throw new MarkFormatException("Please specify a valid task ID number.");
        }
    }

    private static void unmarkTask(String command) throws MarkFormatException {
        String[] parts = command.split(" "); //Should be mark X, where X is a number
        if (parts.length < 2) {
            throw new MarkFormatException("You must specify a task to unmark after typing \"unmark\"");
        }
        try {
            int idx = Integer.parseInt(parts[1]);
            if (idx > list.size() || idx < 1) {
                throw new MarkFormatException("Cannot unmark task with ID =  " + idx + ". It does not exist.");
            } else {
                //Within range, so we mark the task
                Task t = list.get(idx - 1); //0 -based indexing
                if (!t.isDone()) {
                    //Already an unmarked task, inform the user
                    System.out.println("This task has yet to be completed.");
                } else {
                    t.markAsIncomplete(); //Set to incomplete now
                    System.out.println("Ok, I've unmarked this task to be not done yet:");
                }
                System.out.println(t.toString());
            }
        } catch (NumberFormatException e) {
            throw new MarkFormatException("Please specify a valid task ID number.");
        }

    }

    private static void addTodoTask(String command) throws TodoFormatException {
        if (command.length() < 5) {
            throw new TodoFormatException("Please enter a valid todo format as follows: todo <description>");
        }
        else {
            String description = command.substring(5).trim();
            Task t = new Todo(description);
            list.add(t);
            System.out.println("Got it. I've added this task:\n" + t.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list");
            printLine();
        }
    }

    private static void addDeadlineTask(String command) throws DeadlineFormatException {
        int byIndex = command.indexOf("/by");
        if (byIndex == -1) {
            throw new DeadlineFormatException("Invalid format. Use deadline <description> /by YYYY-MM-DD");
        }
        else {
            String description = command.substring(9, byIndex).trim(); //returns the description just before "/by" and after "deadline "
            String dueBy = command.substring(byIndex + 3).trim(); // Start index is right after /by
            try {
                Task t = new Deadline(description, dueBy);
                list.add(t);
                System.out.println("Got it. I've added this task:\n" + t.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
                printLine();
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date Format. Please use YYYY-MM-DD (e.g. 2025-01-28 to represent 28th Jan 2025) to represent the deadline date.");
                printLine();
            }

        }
    }

    private static void addEventTask(String command) throws EventFormatException {
        //Get the start and end dates
        int fromIndex = command.indexOf("/from");
        int toIndex = command.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            throw new EventFormatException("Please enter a valid event format as follows: event <description>  /from <start date> /to <due date>");
        } else {
            String description = command.substring(6, fromIndex).trim();
            String start = command.substring(fromIndex + 5, toIndex).trim();
            String end = command.substring(toIndex + 3).trim();
            try {
                Task t = new Event(description, start, end);
                list.add(t);
                System.out.println("Got it. I've added this task:\n" + t.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
                printLine();
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date Format. Please use YYYY-MM-DD (e.g. 2025-01-28 to represent 28th Jan 2025) to represent the 2 dates required for an Event task." );
                printLine();
            }

        }
    }

    private static void deleteTask(String command) throws DeleteFormatException {
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            throw new DeleteFormatException("You must specify a task to delete after typing \"delete\"");
        }
        try {
            int id = Integer.parseInt(parts[1]);
            if (id > list.size() || id < 1) {
                throw new DeleteFormatException("Cannot delete task with ID =  " + id + ". It does not exist.");
            } else {
                Task toDelete = list.remove(id - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(toDelete.toString());
            }
        } catch (NumberFormatException e) {
            throw new DeleteFormatException("Please specify a valid task ID number.");
        }
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    private static Command parseCommand(String commandWord) throws UnknownCommandException {
        try {
            //Convert input "todo" --> "TODO" and find the enum
            return Command.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("Your command \"" + commandWord + "\" is invalid.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<>();
        loadTasks();
        greetUser();
        while (true) {
            String instruction = sc.nextLine();
            String[] parts = instruction.split(" ", 2); //Split into command + arguments
            String commandWord = parts[0];
            try {
                //Convert the string into an enum
                Command command = parseCommand(commandWord);
                switch (command) {
                    case BYE:
                        sayBye();
                        sc.close();
                        return; //Break out of the loop
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
                System.out.println(e.getMessage());
                printLine();
            }
        }
    }
}
