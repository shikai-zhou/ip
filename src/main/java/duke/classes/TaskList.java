package duke.classes;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.exceptions.EmptyDescriptionException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    public static int numOfTasks;
    public static ArrayList<Task> tasks;

    public TaskList () {
        tasks = new ArrayList<>();
        numOfTasks = 0;
    }

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

    public void addTodo(String in) throws EmptyDescriptionException {
        //add a new todo task
        int dividerPosition = in.indexOf(" ");
        if (dividerPosition <= 0) {
            throw new EmptyDescriptionException();
        }
        boolean isDone = false;
        if (in.startsWith("[T]")) {
            String c = Character.toString(in.charAt(4));
            if (c.equals("\u2713")) {
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

    public void addDeadline (String in) {
        //add a new deadline task
        Deadline t;
        boolean isFromFile = in.startsWith("[D]");
        boolean isDone = false;
        if (isFromFile) {
            String c = Character.toString(in.charAt(4));
            if (c.equals("\u2713")) {
                isDone = true;
            }
            int dividerPosition1 = in.indexOf(" ");
            int dividerPosition2 = in.indexOf(":");
            t = new Deadline(in.substring((dividerPosition1 + 1), dividerPosition2-3),
                    in.substring(dividerPosition2 + 2, in.length()-1));
        }
        else {
            int dividerPosition1 = in.indexOf(" ");
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

    public void addEvent(String in) {
        //add a new event task
        Event t;
        boolean isDone = false;
        if (in.startsWith("[E]")) {
            String c = Character.toString(in.charAt(4));
            if (c.equals("\u2713")) {
                isDone = true;
            }
            int dividerPosition1 = in.indexOf(" ");
            int dividerPosition2 = in.indexOf(":");
            t = new Event(in.substring((dividerPosition1+1),dividerPosition2-3),
                    in.substring(dividerPosition2 + 2,in.length()-1));
        }
        else {
            int dividerPosition1 = in.indexOf(" ");
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

    public void printTask (Task task) {
        //prints out the task
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + (numOfTasks) + " task(s) in the list.");
    }

    public void writeToFile(String filePath) throws IOException {
        //writes the task to the file
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toString()+System.lineSeparator());
        }
        fw.close();
    }
}
