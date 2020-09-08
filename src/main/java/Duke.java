import java.util.Scanner;
import java.util.ArrayList;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        int numOfTasks = 0;
        // parses the user input
        while (true) {
            String input = in.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    list(numOfTasks, tasks);
                } else if (input.matches("done(.*)")) {
                    markDone(input, tasks);
                } else if (input.matches("todo.*")) {
                    try {
                        addTodo(input, tasks, numOfTasks);
                        numOfTasks++;
                    } catch (EmptyDescriptionException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (input.matches("deadline.*")) {
                    addDeadline(input, tasks, numOfTasks);
                    numOfTasks++;
                } else if (input.matches("event.*")) {
                    addEvent(input, tasks, numOfTasks);
                    numOfTasks++;
                } else {
                    throw new UnkownCommandException();
                }
            } catch (UnkownCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void list(int numOfTasks, ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < numOfTasks; j++) {
            System.out.println((j+1)+"." + tasks.get(j).toString() );
        }
    }

    public static void markDone(String in, ArrayList<Task> tasks) {
        int dividerPosition = in.indexOf(" ");
        String number = in.substring(dividerPosition+1);
        int i = Integer.parseInt(number);
        i = i - 1; //the user counts from 1 instead of 0
        try {
            tasks.get(i).setAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, I can't find such a task.");
            return;
        }
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  "+tasks.get(i).toString());
    }

    public static void addTask (String input, ArrayList<Task> tasks, int numOfTasks) {
        tasks.add(new Task(input));
        System.out.println("added: " + tasks.get(numOfTasks).getDescription());
        numOfTasks++;
    }

    public static void addTodo(String in, ArrayList<Task> tasks, int numOfTasks) throws EmptyDescriptionException{
        int dividerPosition = in.indexOf(" ");
        if (dividerPosition <= 0) {
            throw new EmptyDescriptionException();
        }
        tasks.add(new Todo(in.substring(dividerPosition+1)));
        printEvent(tasks, numOfTasks);
        numOfTasks++;
    }

    public static void addDeadline(String in, ArrayList<Task> tasks, int numOfTasks) {
        int dividerPosition1 = in.indexOf(" ");
        int dividerPosition2 = in.indexOf("/");
        tasks.add(new Deadline(in.substring((dividerPosition1+1),dividerPosition2), in.substring(dividerPosition2+4)));
        printEvent(tasks, numOfTasks);
        numOfTasks++;
    }

    public static void addEvent(String in, ArrayList<Task> tasks, int numOfTasks) {
        int dividerPosition1 = in.indexOf(" ");
        int dividerPosition2 = in.indexOf("/");
        tasks.add(new Event(in.substring((dividerPosition1+1),dividerPosition2), in.substring(dividerPosition2+4)));
        printEvent(tasks, numOfTasks);
        numOfTasks++;
    }

    public static void printEvent (ArrayList<Task> tasks, int numOfTasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  "+tasks.get(numOfTasks).toString());
        System.out.println("Now you have " + (numOfTasks+1) + " task(s) in the list.");
    }
}
