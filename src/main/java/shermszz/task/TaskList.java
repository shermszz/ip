package shermszz.task;

import java.util.ArrayList;

/**
 * Represents the list of tasks in the Shermszz application.
 * <p>
 * This class encapsulates an {@code ArrayList<Task>} and provides methods to
 * manipulate the list, such as adding, deleting, and retrieving tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     * Initializes the internal list to an empty {@code ArrayList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} containing the given tasks.
     * Useful when loading existing data from storage.
     *
     * @param tasks An {@code ArrayList} of {@code Task} objects to populate the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t The {@code Task} object to be added.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes the task at the specified position in the list.
     *
     * @param index The zero-based index of the task to be removed.
     * @return The {@code Task} that was removed.
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified position in the list.
     *
     * @param index The zero-based index of the task to return.
     * @return The {@code Task} at the specified position.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }
}
