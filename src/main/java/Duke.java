import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] arr ;
        arr = new String[100];
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        int i = 0;
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) break;
            if (input.equals("list")) list(i, arr);
            else
            {
                arr[i] = input;
                System.out.println("added: " + arr[i]);
                i++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void list(int size, String[] arr)
    {
        for (int j = 0; j < size; j++)
        {
            System.out.println((j+1)+". "+arr[j]);
        }
    }
}
