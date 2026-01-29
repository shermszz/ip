package shermszz.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTask_sizeIncreases() {
        TaskList t = new TaskList();
        assertEquals(0, t.getSize());

        t.add(new Task("read book"));

        assertEquals(1, t.getSize());
    }

    @Test
    public void removeTask_sizeDecreases() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.add(new Todo("sleep"));
        assertEquals(2, tasks.getSize());

        tasks.remove(0);
        assertEquals(1, tasks.getSize());
    }

    @Test
    public void find_matchingKeyword_matchingTasksReturned() {
        // 1. Setup: Create a TaskList with dummy data
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.add(new Todo("return book"));
        tasks.add(new Todo("eat apple"));

        // 2. Execution: Search for "book"
        TaskList result = tasks.find("book");

        // 3. Verification: Should find 2 tasks
        assertEquals(2, result.getSize());
        assertEquals("[T][] read book", result.get(0).toString());
    }

    @Test
    public void find_noMatchingKeyword_emptyListReturned() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));

        // Search for something that doesn't exist
        TaskList result = tasks.find("gym");

        assertEquals(0, result.getSize());
    }
}
