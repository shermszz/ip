package shermszz.parser;

/**
 * Represents the valid commands that can be executed by the user.
 * These commands drive the main control flow of the application.
 */
public enum Command {
    /** Command to add a Todo task. */
    TODO,
    /** Command to add a Deadline task. */
    DEADLINE,
    /** Command to add an Event task. */
    EVENT,
    /** Command to mark a task as done. */
    MARK,
    /** Command to mark a task as not done. */
    UNMARK,
    /** Command to delete a task. */
    DELETE,
    /** Command to list all tasks. */
    LIST,
    /** Command to view tasks on a specific schedule. */
    SCHEDULE,
    /** Command to find tasks that match the description */
    FIND,
    /** Command to exit the application. */
    BYE
}
