package shermszz.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import shermszz.exceptions.TodoFormatException;
import shermszz.exceptions.DeadlineFormatException;
import shermszz.exceptions.DeleteFormatException;
import shermszz.exceptions.EventFormatException;
import shermszz.exceptions.UnknownCommandException;
import shermszz.exceptions.MarkFormatException;
import shermszz.exceptions.ScheduleFormatException;

public class Parser {
    public static Command parse(String fullCommand) throws UnknownCommandException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        try {
            //Convert input "todo" --> "TODO" and find the enum
            return Command.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("Your command \"" + commandWord + "\" is invalid.");
        }
    }

    public static String parseTodo(String command) throws TodoFormatException {
        if (command.length() < 5) {
            throw new TodoFormatException("Please enter a valid todo format as follows: todo <description>");
        } else {
            return command.substring(5).trim();
        }
    }

    public static String[] parseDeadline(String command) throws DeadlineFormatException {
        int byIndex = command.indexOf("/by");
        if (byIndex == -1) {
            throw new DeadlineFormatException("Invalid format. Use deadline <description> /by YYYY-MM-DD");
        } else {
            String description = command.substring(9, byIndex).trim(); //returns the description just before "/by" and after "deadline "
            String dueBy = command.substring(byIndex + 3).trim(); // Start index is right after /by
            return new String[]{description, dueBy};
        }
    }

    public static String[] parseEvent(String command) throws EventFormatException {
        int fromIndex = command.indexOf("/from");
        int toIndex = command.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            throw new EventFormatException("Please enter a valid event format as follows: event <description>  /from <start date> /to <due date>");
        } else {
            String description = command.substring(6, fromIndex).trim();
            String start = command.substring(fromIndex + 5, toIndex).trim();
            String end = command.substring(toIndex + 3).trim();
            return new String[]{description, start, end};
        }
    }

    public static int parseMarking(String command, int size) throws MarkFormatException {
        String[] parts = command.split(" "); //Should be mark X, where X is a number
        if (parts.length < 2) {
            throw new MarkFormatException("You must specify a task to mark after typing \"mark\"");
        }
        try {
            int idx = Integer.parseInt(parts[1]); //Could throw NumberFormatExecption if a digit isnt entered
            if (idx > size || idx < 1) {
                throw new MarkFormatException("Cannot mark task with ID = " + idx + ". It does not exist.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new MarkFormatException("Please specify a valid task ID number.");
        }
    }

    public static int parseUnmarking(String command, int size) throws MarkFormatException {
        String[] parts = command.split(" "); //Should be mark X, where X is a number
        if (parts.length < 2) {
            throw new MarkFormatException("You must specify a task to unmark after typing \"unmark\"");
        }
        try {
            int idx = Integer.parseInt(parts[1]);
            if (idx > size || idx < 1) {
                throw new MarkFormatException("Cannot unmark task with ID =  " + idx + ". It does not exist.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new MarkFormatException("Please specify a valid task ID number.");
        }
    }

    public static int parseDeletion(String command, int size) throws DeleteFormatException {
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            throw new DeleteFormatException("You must specify a task to delete after typing \"delete\"");
        }
        try {
            int id = Integer.parseInt(parts[1]);
            if (id > size || id < 1) {
                throw new DeleteFormatException("Cannot delete task with ID =  " + id + ". It does not exist.");
            }
            return id;
        } catch (NumberFormatException e) {
            throw new DeleteFormatException("Please specify a valid task ID number.");
        }
    }

    public static LocalDate parseSchedule(String command) throws ScheduleFormatException {
        //shermszz.parser.Command format: "schedule 2025-01-01" --> Will list all deadline/event tasks that are due by 2025-01-01
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            throw new ScheduleFormatException("Please specify a date: schedule YYYY-MM-DD");
        }
        String dateAsString = parts[1].trim();
        try {
            LocalDate date = LocalDate.parse(dateAsString);
            return date;
        } catch (DateTimeParseException e) {
            throw new ScheduleFormatException("Invalid Date Format. Please use YYYY-MM-DD.");
        }
    }
}
