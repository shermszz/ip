package shermszz.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

import shermszz.exceptions.*;

public class ParserTest {

    @Test
    /**
     * Testing Parser.parseTodo directly
     * Scenario: Input is "todo read book"
     * Expected: Returns "read book"
     */
    public void parse_todo_success() throws TodoFormatException {
        assertEquals("read book", Parser.parseTodo("todo read book"));
    }

    @Test
    public void parseTodo_emptyDescription() {
        try {
            Parser.parseTodo("todo");
            fail(); // Fail if no exception occurs
        } catch (TodoFormatException e) {
            assertEquals("Todo Format Error: Please enter a valid todo format as follows: todo <description>", e.getMessage());
        }
    }

    @Test
    /**
     * Testing Parser.parseDeadline directly
     * Scenario: Input is "deadline install JUnit /by 2025-01-29"
     * Expected: Returns a String array of size 2, [install JUnit, 2025-01-29]
     */
    public void parse_deadline_success() throws DeadlineFormatException {
        assertArrayEquals(new String[]{"install JUnit", "2025-01-29"}, Parser.parseDeadline("deadline install JUnit /by 2025-01-29"));
    }

    @Test
    public void parseDeadline_missingBy() {
        try {
            Parser.parseDeadline("deadline read book"); // Missing /by
            fail();
        } catch (DeadlineFormatException e) {
            assertEquals("Deadline Format Error: Invalid format. Use deadline <description> /by YYYY-MM-DD", e.getMessage());
        }
    }

    @Test
    /**
     * Testing Parser.parseEvent directly
     * Scenario: Input is "event say hello /from 2025-01-01 /to 2026-01-01"
     * Expected: Returns a String array of size 2, [install JUnit, 2025-01-29]
     */
    public void parse_event_success() throws EventFormatException {
        assertArrayEquals(new String[]{"say hello", "2025-01-01", "2026-01-01"}, Parser.parseEvent("event say hello /from 2025-01-01 /to 2026-01-01"));
    }

    @Test
    /**
     * Testing: Parser parseMarking or parseUnmarking directly
     * Scenario: Input is mark 3 then unmark 3
     * Expected: Returns the 1-based index number of the task we want to mark or unmark
     */
    public void parse_marking_success() throws MarkFormatException {
        int mark = Parser.parseMarking("mark 3", 5);
        assertEquals(3, mark);

        int unmark = Parser.parseUnmarking("unmark 3", 5);
        assertEquals(3, unmark);
    }

    @Test
    /**
     * Testing logic if (idx > size)
     */
    public void parse_marking_invalid_index() {
        try {
            Parser.parseMarking("mark 10", 5);
            fail();
        } catch (MarkFormatException e) {
            assertEquals("Mark Format Error: Cannot mark task with ID = 10. It does not exist.", e.getMessage());
        }
    }

    @Test
    /**
     * Testing Parser.parse directly
     * Scenario: Input will be an invalid command: Hello there
     * Expected: Throws UnknownCommandException with an error message of "Unknown Command Error: Your command \"" + commandWord + "\" is invalid."
     */
    public void parse_invalid_command() {
        try {
            Parser.parse("Hello there");
            fail(); //Should not reach here
        } catch (UnknownCommandException e) {
            assertEquals("Unknown Command Error: Your command \"Hello\" is invalid.", e.getMessage());
        }
    }
}
