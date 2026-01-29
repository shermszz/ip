package shermszz.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toString_newEvent_correctFormat() {
        Event t = new Event("read book", "2025-01-01", "2026-01-01");
        // Assuming your toString format is "[E][] read book (from: Jan 01 2025 to: Jan 01 2026)"
        assertEquals("[E][] read book (from: Jan 01 2025 to: Jan 01 2026)", t.toString());
    }

    @Test
    public void toFileFormat_validDeadline_correctString() {
        Event t = new Event("read book", "2025-01-01", "2026-01-01");
        // Based on Storage.java: "E | 0 | read book | 2025-01-01 | 2026-01-01"
        assertEquals("E | 0 | read book | 2025-01-01 | 2026-01-01", t.toFileFormat());
    }
}
