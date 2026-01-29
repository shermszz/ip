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

/**
 * Parses user input into actionable commands and extracts task details.
 * <p>
 * This class serves as the interface between raw user input and the application's logic.
 * It handles the breakdown of command strings, validation of formats, and extraction
 * of arguments for task creation and modification.
 */
public class Parser {

    /**
     * Parses the full command string to identify the specific command type.
     * Converts the first word of the input into a standard {@code Command} enum.
     *
     * @param fullCommand The full input string entered by the user.
     * @return The {@code Command} corresponding to the user's input.
     * @throws UnknownCommandException If the command word does not match any known command.
     */
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

    /**
     * Parses the arguments for a Todo command.
     * Extracts the description part of the command string.
     *
     * @param command The full command string (e.g., "todo read book").
     * @return The description of the todo task.
     * @throws TodoFormatException If the description is empty or the format is invalid.
     */
    public static String parseTodo(String command) throws TodoFormatException {
        if (command.length() < 5) {
            throw new TodoFormatException("Please enter a valid todo format as follows: todo <description>");
        } else {
            return command.substring(5).trim();
        }
    }

    /**
     * Extracts the description and deadline date from a Deadline command.
     * Expects the format: "deadline [description] /by [date]".
     *
     * @param command The full command string.
     * @return A String array where index 0 is the description and index 1 is the due date.
     * @throws DeadlineFormatException If the "/by" delimiter is missing.
     */
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

    /**
     * Extracts the description, start time, and end time from an Event command.
     * Expects the format: "event [description] /from [start] /to [end]".
     *
     * @param command The full command string.
     * @return A String array where index 0 is description, 1 is start time, and 2 is end time.
     * @throws EventFormatException If "/from" or "/to" delimiters are missing or unordered.
     */
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

    /**
     * Parses the task index from a mark command.
     * Validates that the index is a number and exists within the current task list.
     *
     * @param command The full command string (e.g., "mark 1").
     * @param size    The current size of the task list (used for validation).
     * @return The 1-based index of the task to be marked.
     * @throws MarkFormatException If the index is missing, not a number, or out of bounds.
     */
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

    /**
     * Parses the task index from an unmark command.
     * Validates that the index is a number and exists within the current task list.
     *
     * @param command The full command string (e.g., "unmark 1").
     * @param size    The current size of the task list (used for validation).
     * @return The 1-based index of the task to be unmarked.
     * @throws MarkFormatException If the index is missing, not a number, or out of bounds.
     */
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

    /**
     * Parses the task index from a delete command.
     * Validates that the index is a number and exists within the current task list.
     *
     * @param command The full command string (e.g., "delete 1").
     * @param size    The current size of the task list (used for validation).
     * @return The 1-based index of the task to be deleted.
     * @throws DeleteFormatException If the index is missing, not a number, or out of bounds.
     */
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

    /**
     * Parses the schedule command to extract the target date.
     * Expects input in the format "schedule YYYY-MM-DD".
     *
     * @param command The full command string.
     * @return The parsed {@code LocalDate} object.
     * @throws ScheduleFormatException If the date argument is missing or in an invalid format.
     */
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
