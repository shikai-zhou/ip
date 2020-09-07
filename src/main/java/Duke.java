import java.util.Scanner;
import java.util.ArrayList;

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
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                list(numOfTasks, tasks);
            } else if (input.matches("done(.*)")) {
                markDone(input, tasks);
            } else if (input.matches("todo.*")){
                addTodo(input, tasks, numOfTasks);
                numOfTasks++;
            } else if (input.matches("deadline.*")) {
                addDeadline(input, tasks, numOfTasks);
                numOfTasks++;
            } else if (input.matches("event.*")) {
                addEvent(input, tasks, numOfTasks);
                numOfTasks++;
            } else {
                addTask(input, tasks, numOfTasks);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list(int size, ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < size; j++) {
            System.out.println((j+1)+"." + tasks.get(j).toString() );
        }
    }

    public static void markDone(String in, ArrayList<Task> tasks) {
        int dividerPosition = in.indexOf(" ");
        String number = in.substring(dividerPosition+1);
        int i = Integer.parseInt(number);
        i = i - 1; //the user counts from 1 instead of 0
        tasks.get(i).setAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  "+tasks.get(i).toString());
    }

    public static void addTask (String input, ArrayList<Task> tasks, int numOfTasks) {
        tasks.add(new Task(input));
        System.out.println("added: " + tasks.get(numOfTasks).getDescription());
        numOfTasks++;
    }

    public static void addTodo(String in, ArrayList<Task> tasks, int numOfTasks) {
        int dividerPosition = in.indexOf(" ");
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
        System.out.println("Now you have " + (numOfTasks+1) + " tasks in the list.");
    }
}
