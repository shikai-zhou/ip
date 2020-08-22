import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] arr ;
        arr = new Task[100];
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        int i = 0;
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) break;
            else if (input.equals("list")) list(i, arr);
            else if (input.matches("done(.*)")) done(input.charAt(input.length()-1), arr);
            else {
                arr[i] = new Task(input);
                System.out.println("added: " + arr[i].getDescription());
                i++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list(int size, Task[] arr) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < size; j++) {
            System.out.println((j+1)+".["+arr[j].getStatusIcon()+"] "+arr[j].getDescription());
        }
    }

    public static void done(char n, Task[] arr) {
        int i = Character.getNumericValue(n) - 1; //the user counts from 1 instead of 0
        arr[i].setAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  ["+arr[i].getStatusIcon()+"] "+arr[i].getDescription());
    }
}
