package shermszz.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import shermszz.task.Task;
import shermszz.task.TaskList;
import shermszz.task.Todo;
import shermszz.task.Deadline;
import shermszz.task.Event;
import shermszz.exceptions.ShermszzException;
import shermszz.exceptions.FileSaveException;

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
        File f = new File(this.filepath);

        if (!f.exists()) {
            return loadedTasks; //If file does not exist, just return an empty list
        }
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                try {
                    String[] parts = line.split(" \\| "); //To split the array up based on " | ".
                    //E.g. T | 1 | read book --> ["T", "1", "read book"]
                    String type = parts[0]; //Either a shermszz.task.Todo or shermszz.task.Deadline or shermszz.task.Event task
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
                    }

                    if (t != null) {
                        if (isDone) t.markAsDone();
                        loadedTasks.add(t);
                    }
                } catch (Exception e) {
                    // System.out.println("Corrupted data found and skipped: " + line);
                    // File could be corrupted, but we just let the problem continue to run, silently ignore this corrupted line
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
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter fw = new FileWriter(filepath);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.get(i).toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
           throw new FileSaveException("Error saving tasks: " + e.getMessage());
        }
    }
}
