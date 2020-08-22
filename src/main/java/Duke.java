import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) break;
            System.out.println(input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
