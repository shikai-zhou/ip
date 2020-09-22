import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import duke.classes.TaskList;
import duke.classes.Ui;
import duke.classes.Storage;
import duke.classes.Parser;

public class Duke {
    public static boolean isTerminated = false;
    public static Storage storage;
    public static TaskList tasks;
    public static Ui ui;
    public static Parser p = new Parser();
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.openFile(); //try to open the txt file
        } catch (FileNotFoundException e) { // if the file does not exist create one
            try {
                File f = new File("data/tasks.txt");
                if (f.createNewFile()) {
                    System.out.println("New file created.");
                }
            } catch (IOException g) {
                System.out.println("Unable to create file.");
            }
        }
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        ui.welcome();
        while (!isTerminated) {
            String input = in.nextLine();
            isTerminated = p.parse(input, tasks);
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
