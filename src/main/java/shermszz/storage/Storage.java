package shermszz.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import shermszz.exceptions.FileSaveException;
import shermszz.exceptions.ShermszzException;
import shermszz.task.Deadline;
import shermszz.task.Event;
import shermszz.task.Task;
import shermszz.task.TaskList;
import shermszz.task.Todo;

/**
 * Handles the loading and saving of task data to the local hard disk.
 */

public class Storage {
    private String filepath;

    /**
     * Creates a new Storage instance.
     *
     * @param filepath The file path where data is stored.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the data file.
     * Parses each line of the file and converts it into specific Task objects (Todo, Deadline, Event).
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws ShermszzException If an I/O error occurs during reading.
     */
    public ArrayList<Task> load() throws ShermszzException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(this.filepath);

        if (!file.exists()) {
            return loadedTasks; //If file does not exist, just return an empty list
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    String[] parts = line.split(" \\| "); //To split the array up based on " | ".
                    //E.g. T | 1 | read book --> ["T", "1", "read book"]
                    String type = parts[0]; //Either a Todo or.Deadline or Event task
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    Task t = null;
                    switch (type) {
                    case "T":
                        //E.g. T | 0 | test todo
                        t = new Todo(description);
                        break;
                    case "D":
                        //E.g. D | 0 | test deadline | 2025-01-01
                        t = new Deadline(description, parts[3]);
                        break;
                    case "E":
                        //E.g. E | 0 | test event | 2025-01-01 | 2026-01-01
                        t = new Event(description, parts[3], parts[4]);
                        break;
                    default:
                    }

                    if (t != null) {
                        if (isDone) {
                            t.markAsDone();
                        }
                        loadedTasks.add(t);
                    }
                } catch (Exception e) {
                    // File could be corrupted, but we just let the problem continue to run,
                    // silently ignore this corrupted line
                }
            }
        } catch (IOException e) {
            throw new ShermszzException("Error loading tasks: " + e.getMessage());
        }
        return loadedTasks;
    }

    /**
     * Saves the current list of tasks to the data file.
     * Overwrites the existing file with the current state of the TaskList.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws FileSaveException If an I/O error occurs during writing.
     */
    public void save(TaskList tasks) throws FileSaveException {
        assert tasks != null : "Cannot save a null TaskList";
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter fileWriter = new FileWriter(filepath);
            for (int i = 0; i < tasks.getSize(); i++) {
                fileWriter.write(tasks.get(i).toFileFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new FileSaveException("Error saving tasks: " + e.getMessage());
        }
    }
}
