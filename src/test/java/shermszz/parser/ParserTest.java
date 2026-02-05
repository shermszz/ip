package shermszz.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import shermszz.exceptions.DeadlineFormatException;
import shermszz.exceptions.EventFormatException;
import shermszz.exceptions.MarkFormatException;
import shermszz.exceptions.ShermszzException;
import shermszz.exceptions.TodoFormatException;

public class ParserTest {
    @Test
    public void parseTodo_validFormat_success() throws TodoFormatException {
        assertEquals("read book", Parser.parseTodo("todo read book"));
    }

    @Test
    public void parseTodo_emptyDescription_todoFormatExceptionReturned() {
        try {
            Parser.parseTodo("todo");
            fail(); // Fail if no exception occurs
        } catch (TodoFormatException e) {
            assertEquals("Todo Format Error: Please enter a valid todo format as follows: todo <description>",
                    e.getMessage());
        }
    }

    @Test
    public void parseDeadline_validFormat_success() throws DeadlineFormatException {
        assertArrayEquals(new String[]{"install JUnit", "2025-01-29"},
                Parser.parseDeadline("deadline install JUnit /by 2025-01-29"));
    }

    @Test
    public void parseDeadline_missingBy_deadlineFormatExceptionReturned() {
        try {
            Parser.parseDeadline("deadline read book"); // Missing /by
            fail();
        } catch (DeadlineFormatException e) {
            assertEquals("Deadline Format Error: Invalid format. Use deadline <description> /by YYYY-MM-DD",
                    e.getMessage());
        }
    }

    @Test
    public void parseEvent_validFormat_success() throws EventFormatException {
        assertArrayEquals(new String[]{"say hello", "2025-01-01", "2026-01-01"},
                Parser.parseEvent("event say hello /from 2025-01-01 /to 2026-01-01"));
    }

    @Test
    public void parseMark_validTaskIndex_success() throws MarkFormatException {
        int mark = Parser.parseMarking("mark 3");
        assertEquals(3, mark);

        int unmark = Parser.parseUnmarking("unmark 3");
        assertEquals(3, unmark);
    }


    @Test
    public void parse_invalidCommand_unknownCommandExceptionReturned() {
        try {
            Parser.parseCommand("Hello there");
            fail(); //Should not reach here
        } catch (ShermszzException e) {
            assertEquals("Unknown Command Error: I'm sorry, but your command: hello is invalid.", e.getMessage());
        }
    }

    @Test
    public void parseFind_validInput_success() throws Exception {
        // Test that the keyword is correctly extracted
        String command = "find book";
        String expected = "book";
        assertEquals(expected, Parser.parseFind(command));
    }

    @Test
    public void parseFind_missingKeyword_exceptionThrown() {
        // Test that "find" without a keyword throws an exception
        try {
            Parser.parseFind("find");
            fail(); // The test should fail if no exception is thrown
        } catch (ShermszzException e) {
            // Verify the error message matches exactly what you wrote in Parser.java
            assertEquals("Please specify a keyword to search for. (E.g. find book)", e.getMessage());
        }
    }


}
