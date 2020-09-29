package duke.classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    private static File f;
    public Storage (String input) {
        filePath = input;
    }

    public void openFile() throws FileNotFoundException {
        // create a File for the given file path
        f = new File(filePath);
    }

    public TaskList load() throws FileNotFoundException{
        //load the contents of the file into the tasklist
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        TaskList tasks = new TaskList();
        Parser p = new Parser();
        while (s.hasNext()) { // read in input
            p.parse(s.nextLine(), tasks);
        }
            return tasks;
    }
}
