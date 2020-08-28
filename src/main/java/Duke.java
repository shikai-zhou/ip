import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks;
        tasks = new Task[100];
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        int size = 0;
        // parses the user input
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                list(size, tasks);
            } else if (input.matches("done(.*)")) {
                done(input, tasks);
            } else if (input.matches("todo.*")){
                todo(input, tasks, size);
                size++;
            } else if (input.matches("deadline.*")) {
                deadline(input, tasks, size);
                size++;
            } else if (input.matches("event.*")) {
                event(input, tasks, size);
                size++;
            } else {
                tasks[size] = new Task(input);
                System.out.println("added: " + tasks[size].getDescription());
                size++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list(int size, Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < size; j++) {
            System.out.println((j+1)+"." + tasks[j].toString() );
        }
    }

    public static void done(String in, Task[] tasks) {
        int i = 0;
        int dividerPosition = in.indexOf(" ");
        String number = in.substring(dividerPosition+1);
        i = Integer.parseInt(number);
        i = i - 1; //the user counts from 1 instead of 0
        tasks[i].setAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  "+tasks[i].toString());
    }

    public static void todo(String in, Task[] tasks, int size) {
        int dividerPosition = in.indexOf(" ");
        tasks[size] = new Todo(in.substring(dividerPosition+1));
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + tasks[size].toString());
        System.out.println("Now you have " + (size+1) + " tasks in the list.");
    }

    public static void deadline(String in, Task[] tasks, int size) {
        int dividerPosition1 = in.indexOf(" ");
        int dividerPosition2 = in.indexOf("/");
        tasks[size] = new Deadline(in.substring((dividerPosition1+1),dividerPosition2), in.substring(dividerPosition2+4));
        System.out.println("Got it. I've added this task: ");
        System.out.println("  "+tasks[size].toString());
        System.out.println("Now you have " + (size+1) + " tasks in the list.");
    }

    public static void event(String in, Task[] tasks, int size) {
        int dividerPosition1 = in.indexOf(" ");
        int dividerPosition2 = in.indexOf("/");
        tasks[size] = new Event(in.substring((dividerPosition1+1),dividerPosition2), in.substring(dividerPosition2+4));
        System.out.println("Got it. I've added this task: ");
        System.out.println("  "+tasks[size].toString());
        System.out.println("Now you have " + (size+1) + " tasks in the list.");
    }
}
