package shermszz.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toString_newDeadline_correctFormat() {
        Deadline t = new Deadline("read book", "2025-01-01");
        // Assuming your toString format is "[D][] read book (by: Jan 01 2025)"
        assertEquals("[D][] read book (by: Jan 01 2025)", t.toString());
    }

    @Test
    public void toFileFormat_validDeadline_correctString() {
        Deadline t = new Deadline("read book", "2025-01-01");
        // Based on Storage.java: D | 0 | read book | 2025-01-01"
        assertEquals("D | 0 | read book | 2025-01-01", t.toFileFormat());
    }
}
