package duke.classes;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.exceptions.EmptyDescriptionException;
import java.io.File;
import java.util.regex.Matcher;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TaskList {
    public static int numOfTasks;
    public static ArrayList<Task> tasks;

    public TaskList () {
        tasks = new ArrayList<>();
        numOfTasks = 0;
    }

    /**
     * Delete a task
     * @param input
     */
    public void deleteTask (String input) {
        //remove task from list by index
        int dividerPosition = input.indexOf(" ");
        int index = Integer.parseInt(input.substring(dividerPosition+1)) - 1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(index).toString());
        tasks.remove(index);
        numOfTasks--;
        System.out.println("Now you have " + (numOfTasks) +" task(s) in the list.");
    }

    /**
     * List out all tasks
     */
    public void list() {
        //prints out all tasks in ArrayList
        if (numOfTasks == 0) {
            System.out.println("Your list is empty.");
        }
        else {
            System.out.println("Here are the task(s) in your list:");
            for (int j = 0; j < tasks.size(); j++) {
                System.out.println((j + 1) + "." + tasks.get(j).toString());
            }
        }
    }

    /**
     * Add a todo into task list
     * @param in
     * @throws EmptyDescriptionException
     */
    public void addTodo(String in) throws EmptyDescriptionException {
        //add a new todo task
        int dividerPosition = in.indexOf(" ");
        if (dividerPosition <= 0) {
            throw new EmptyDescriptionException();
        }
        boolean isDone = false;
        if (in.startsWith("[T]")) {
            String c = Character.toString(in.charAt(4));
            if (c.equals("Y")) {
                isDone = true;
            }
        }
        Todo t = new Todo(in.substring(dividerPosition + 1));
        if (isDone) {
            t.setAsDone();
        }
        numOfTasks++;
        printTask(t);
        tasks.add(t);
    }

    /**
     * Add a deadline into task list
     * @param in
     * @throws EmptyDescriptionException
     */
    public void addDeadline (String in) throws EmptyDescriptionException {
        //add a new deadline task
        Deadline t;
        boolean isFromFile = in.startsWith("[D]");
        boolean isDone = false;
        int dividerPosition1 = in.indexOf(" ");
        if (dividerPosition1 <= 0) {
            throw new EmptyDescriptionException();
        }
        if (isFromFile) {
            String c = Character.toString(in.charAt(4));
            if (c.equals("Y")) {
                isDone = true;
            }
            int dividerPosition2 = in.indexOf(":");
            t = new Deadline(in.substring((dividerPosition1 + 1), dividerPosition2-3),
                    in.substring(dividerPosition2 + 2, in.length()-1));
        }
        else {
            int dividerPosition2 = in.indexOf("/");
            t = new Deadline(in.substring((dividerPosition1 + 1), dividerPosition2),
                    in.substring(dividerPosition2 + 4));
        }
        if (isDone) {
            t.setAsDone();
        }
        numOfTasks++;
        printTask(t);
        tasks.add(t);
    }

    /**
     * Adds an event into tasklist
     * @param in
     * @throws EmptyDescriptionException
     */
    public void addEvent(String in) throws EmptyDescriptionException {
        //add a new event task
        Event t;
        boolean isDone = false;
        int dividerPosition1 = in.indexOf(" ");
        if (dividerPosition1 <= 0) {
            throw new EmptyDescriptionException();
        }
        if (in.startsWith("[E]")) {
            String c = Character.toString(in.charAt(4));
            if (c.equals("Y")) {
                isDone = true;
            }
            int dividerPosition2 = in.indexOf(":");
            t = new Event(in.substring((dividerPosition1+1),dividerPosition2-3),
                    in.substring(dividerPosition2 + 2,in.length()-1));
        }
        else {
            int dividerPosition2 = in.indexOf("/");
            t = new Event(in.substring((dividerPosition1 + 1), dividerPosition2),
                    in.substring(dividerPosition2 + 4));
        }
        if (isDone) {
            t.setAsDone();
        }
        numOfTasks++;
        tasks.add(t);
        printTask(t);
    }

    /**
     * mark as task as done
     * @param in
     */
    public void markDone(String in) {
        //set the task as completed
        int dividerPosition = in.indexOf(" ");
        String number = in.substring(dividerPosition+1);
        int i = Integer.parseInt(number);
        i--; //the user counts from 1 instead of 0
        try {
            tasks.get(i).setAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, I can't find such a task.");
            return;
        }
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(i).toString());
    }

    /**
     * Prints task
     * @param task
     */
    public void printTask (Task task) {
        //prints out the task
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + (numOfTasks) + " task(s) in the list.");
    }

    /**
     * Save to file
     * @param filePath
     */
    public void writeToFile(String filePath) {
        //writes the tasklist to the file to persist data for future use
        File f = new File("./data/tasks.txt");
        try {
            f.getParentFile().mkdirs();
            if (f.createNewFile()) {
                System.out.println("New file created.");
            }
        }catch (IOException e) {
                Ui ui = new Ui();
                ui.showLoadingError();
                return;
            }
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            Ui ui = new Ui();
            ui.showLoadingError();
        }
    }

    /**
     * Searches for a task in tasklist
     * @param input
     */
    public void findTask (String input) {
        //find all tasks that contains user inputted string
        int dividerPosition = input.indexOf(" ");
        Pattern searchPattern = Pattern.compile(input.substring(dividerPosition));
        for (int i = 0; i < tasks.size(); i++) {
            Matcher matcher = searchPattern.matcher((tasks.get(i).toString()));
            if (matcher.find()) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
    }
}
