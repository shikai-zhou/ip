import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks ;
        tasks = new Task[100];
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        int i = 0;
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) break;
            else if (input.equals("list")){
                list(i, tasks);
            }
            else if (input.matches("done(.*)")) {
                done(input, tasks);
            }
            else {
                tasks[i] = new Task(input);
                System.out.println("added: " + tasks[i].getDescription());
                i++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list(int size, Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < size; j++) {
            System.out.println((j+1)+".["+tasks[j].getStatusIcon()+"] "+tasks[j].getDescription());
        }
    }

    public static void done(String in, Task[] tasks) {
        int i = 0;
        for (int j = 1; j < in.length(); j++) {
            char n = in.charAt(in.length() - j);
            if (n == ' ') {
                break;
            }
            i += (Character.getNumericValue(n)*Math.pow(10,(j-1)));
        }
        i = i - 1; //the user counts from 1 instead of 0
        tasks[i].setAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  ["+tasks[i].getStatusIcon()+"] "+tasks[i].getDescription());
    }
}
