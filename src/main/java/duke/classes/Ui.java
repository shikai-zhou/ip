package duke.classes;
import duke.task.Task;
import java.io.IOException;
import java.util.ArrayList;

public class Ui {

    public void welcome () {
        //prints welcome message
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void exit (TaskList tasks) {
        //writes current tasks to file and exit program
        tasks.writeToFile("data/tasks.txt");

        System.out.println("Bye. Hope to see you again soon!");
    }
    public void help () {
        System.out.println("Here are all the valid commands:");
        System.out.println("todo DESCRIPTION");
        System.out.println("event DESCRIPTION /at TIME");
        System.out.println("deadline DESCRIPTION /by TIME");
        System.out.println("list");
        System.out.println("done INDEX");
        System.out.println("find KEYWORD");
        System.out.println("bye");
    }

    public void showLoadingError() {
        System.out.println("Error Loading File");
    }

}
