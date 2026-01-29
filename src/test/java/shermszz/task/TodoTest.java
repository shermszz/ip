package shermszz.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void toString_newTodo_correctFormat() {
        Todo t = new Todo("read book");
        // Assuming your toString format is "[T][] read book"
        assertEquals("[T][] read book", t.toString());
    }

    @Test
    public void markAsDone_mark_toStringUpdates() {
        Todo t = new Todo("read book");
        t.markAsDone();
        // Assuming done mark is "X"
        assertEquals("[T][X] read book", t.toString());
    }

    @Test
    public void toFileFormat_validTodo_correctString() {
        Todo t = new Todo("read book");
        // Based on Storage.java: "T | 0 | read book"
        assertEquals("T | 0 | read book", t.toFileFormat());
    }
}
