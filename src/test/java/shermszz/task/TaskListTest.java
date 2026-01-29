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
}
