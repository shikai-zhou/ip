import java.util.Scanner;
import java.io.FileNotFoundException;
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
            ui.showLoadingError();
        }
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        //run the program
        Scanner in = new Scanner(System.in);
        ui.welcome();
        while (!isTerminated) {
            String input = in.nextLine();
            isTerminated = p.parse(input, tasks);
        }
    }
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
