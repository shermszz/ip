# Spongebob User Guide
**Spongebob** is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is designed to help you keep track of your tasks inside a pineapple under the sea.

## Features

### Adding a Todo task: `todo`

Adds a task without a specific deadline to the list.

**Format:** `todo <description>`

*   **Example:** `todo Catch jellyfish`

**Expected output:**

`Got it. I've added this task: 
[T][ ] Catch jellyfish 
Now you have 5 tasks in the list`

### Adding a Deadline task: `deadline`

Adds a task that needs to be done before a specific date.

**Format:** `deadline <description> /by <date>`

*   The date must be in `YYYY-MM-DD` format.
*   **Example:** `deadline Submit report /by 2026-05-01`

**Expected output:**

`Got it. I've added this task: 
[D][ ] Submit report (by: May 1 2026) 
Now you have 6 tasks in the list`

### Adding an Event task: `event`

Adds a task that starts at a specific time and ends at a specific time.

**Format:** `event <description> /from <start date> /to <end date>`

*   The dates must be in `YYYY-MM-DD` format.
*   **Example:** `event Fry Cook Games /from 2026-06-01 /to 2026-06-03`

**Expected output:**

`Got it. I've added this task: 
[E][ ] Fry Cook Games (from: Jun 1 2026 to: Jun 3 2026) 
Now you have 7 tasks in the list`

### Listing all tasks: `list`

Shows a list of all tasks currently in your record.

**Format:** `list`

**Expected output:**
```
Here are the tasks in your list:
1. [T][X] Buy spatula
2. [D][ ] Feed Gary (by: Jan 1 2026)
3. [E][ ] Boating School (from: Jan 2 2026 to: Jan 2 2026)
```
### Marking a task as done: `mark`

Marks a specific task as completed.

**Format:** `mark <index>`

*   The index refers to the number shown in the displayed task list.
*   The index must be a positive integer (1, 2, 3...).
*   **Example:** `mark 2`

**Expected output:**

`Nice! I've marked this task as done: 
[D][X] Feed Gary (by: Jan 1 2026)`

### Marking a task as not done: `unmark`

Marks a specific task as incomplete.

**Format:** `unmark <index>`

*   The index refers to the number shown in the displayed task list.
*   **Example:** `unmark 2`

**Expected output:**

`OK, I've marked this task as not done yet: 
[D][ ] Feed Gary (by: Jan 1 2026)`

### Deleting a task: `delete`

Removes the specified task from the list.

**Format:** `delete <index>`

*   The index refers to the number shown in the displayed task list.
*   **Example:** `delete 3`

**Expected output:**

`Noted. I've removed this task: 
[E][ ] Boating School (from: Jan 2 2026 to: Jan 2 2026) 
Now you have 2 tasks in the list.`

### Finding tasks: `find`
Finds all tasks in the current list that match the specified description

**Format:** `find <description>`
*   The description is used to filter out all tasks with that matching description
*   **Example:** `find pineapple`

**Expected output:**
```declarative
Here are the matching tasks I have found in my pineapple:
1. [T][] eat pineapple 
```

### Viewing schedule: `view`

Finds and lists all tasks occurring on or after a specific date.

**Format:** `view <date>`

*   The date must be in `YYYY-MM-DD` format.
*   **Example:** `view 2026-01-01`

**Expected output:**

`Here are the tasks upcoming for 2026-01-01: 
[D][ ] Feed Gary (by: Jan 1 2026)`

### Sorting tasks: `sort`

Sorts the task list chronologically based on deadlines or event start dates.

**Format:** `sort`

**Expected output:**
```
I've sorted your tasks by date: 
Here are the tasks in your list:
1. [D][ ] Earlier Deadline (by: Jan 1 2026)
2. [D][ ] Later Deadline (by: Feb 1 2026)
```

### Viewing help: `help`

Displays a list of all available commands and their usage formats.

**Format:** `help`

**Expected output:**
```
Here are the commands I know:
1. todo --> Add a todo task
2. deadline /by --> Add a task with a deadline 
3. event <description> /from <date> /to <date> --> Add an event task with a start and end date
4. list --> Lists all tasks in my record
5. mark <1-based index> --> Mark specified task at the listed index as complete
6. unmark <1-based index> --> Unmark specified task at the listed index as incomplete
7. delete <1-based index> --> Deletes the specified task at the listed index
8. view <date> --> Finds tasks that are going to occur by the specified date
9. find <description> --> Finds all matching tasks that contain the specified description
10. sort --> Displays all tasks again, but in non-descending order of due dates
11. bye --> To exit my pineapple
```

### Exiting the program: `bye`

Exits the application.

**Format:** `bye`

**Expected output:**

`See you next time buddy`