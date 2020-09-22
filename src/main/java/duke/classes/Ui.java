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
        try {
            tasks.writeToFile("data/tasks.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to write to file.");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Error Loading File");
    }

}
