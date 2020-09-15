import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;


public class Duke {
    public static int numOfTasks = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static boolean isTerminated = false;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        try {
            openFile(); //try to open the txt file
        } catch (FileNotFoundException e) { // if the file does not exist create one
            try {
                File f = new File("data/tasks.txt");
                if (f.createNewFile()) {
                    System.out.println("New file created.");
                }
            } catch (IOException g) {
                System.out.println("Unable to create file.");
            }
        }
        while (!isTerminated) {
            String input = in.nextLine();
            parse(input);
        }
    }

    public static void openFile() throws FileNotFoundException {
        File f = new File("data/tasks.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) { // read in input
            parse(s.nextLine());
        }
    }

    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toString()+System.lineSeparator());
        }
        fw.close();
    }

    public static void parse (String input) {
        // parses the user input
        try {
            if (input.equals("bye")) {
                bye();
            } else if (input.equals("list")) {
                list(numOfTasks, tasks);
            } else if (input.matches("done(.*)")) {
                markDone(input, tasks);
            } else if (input.matches("todo.*")||input.startsWith("[T]")) {
                try {
                    addTodo(input, tasks, numOfTasks);
                    numOfTasks++;
                } catch (EmptyDescriptionException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (input.matches("deadline.*")||input.startsWith("[D]")) {
                addDeadline(input, tasks, numOfTasks);
                numOfTasks++;
            } else if (input.matches("event.*")||input.startsWith("[E]")) {
                addEvent(input, tasks, numOfTasks);
                numOfTasks++;
            } else if (input.matches("delete.*")) {
                deleteTask(input);
            } else {
                throw new UnkownCommandException();
            }
        } catch (UnkownCommandException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void bye() {
        try {
            writeToFile("data/tasks.txt");
        } catch (IOException e) {
            System.out.println("Unable to write to file.");
        }
        System.out.println("Bye. Hope to see you again soon!");
        isTerminated = true;
    }

    public static void list(int numOfTasks, ArrayList<Task> tasks) {
        if (numOfTasks == 0) {
            System.out.println("Your list is empty.");
        }
        else {
            System.out.println("Here are the task(s) in your list:");
            for (int j = 0; j < numOfTasks; j++) {
                System.out.println((j + 1) + "." + tasks.get(j).toString());
            }
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
        System.out.println(tasks.get(i).toString());
    }

    public static void addTask (String input, ArrayList<Task> tasks, int numOfTasks) {
        tasks.add(new Task(input));
        System.out.println("added: " + tasks.get(numOfTasks).getDescription());
        numOfTasks++;
    }

    public static void deleteTask (String input) {
        int dividerPosition = input.indexOf(" ");
        int index = Integer.parseInt(input.substring(dividerPosition+1)) - 1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(index).toString());
        tasks.remove(index);
        numOfTasks--;
        System.out.println("Now you have " + (numOfTasks) +" task(s) in the list.");
    }

    public static void addTodo(String in, ArrayList<Task> tasks, int numOfTasks) throws EmptyDescriptionException {
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
        tasks.add(new Todo(in.substring(dividerPosition + 1)));
        if (isDone) {
            tasks.get(numOfTasks).setAsDone();
        }
        printTask(tasks, numOfTasks);
        numOfTasks++;
    }

    public static void addDeadline(String in, ArrayList<Task> tasks, int numOfTasks) {
        boolean isFromFile = in.startsWith("[D]");
        boolean isDone = false;
        if (isFromFile) {
            String c = Character.toString(in.charAt(4));
            if (c.equals("\u2713")) {
                isDone = true;
            }
            int dividerPosition1 = in.indexOf(" ");
            int dividerPosition2 = in.indexOf(":");
            tasks.add(new Deadline(in.substring((dividerPosition1 + 1), dividerPosition2-3),
                    in.substring(dividerPosition2 + 2, in.length()-1)));
        }
        else {
            int dividerPosition1 = in.indexOf(" ");
            int dividerPosition2 = in.indexOf("/");
            tasks.add(new Deadline(in.substring((dividerPosition1 + 1), dividerPosition2),
                    in.substring(dividerPosition2 + 4)));
        }
        if (isDone) {
            tasks.get(numOfTasks).setAsDone();
        }
        printTask(tasks, numOfTasks);
        numOfTasks++;
    }

    public static void addEvent(String in, ArrayList<Task> tasks, int numOfTasks) {
        boolean isDone = false;
        if (in.startsWith("[E]")) {
            String c = Character.toString(in.charAt(4));
            if (c.equals("\u2713")) {
                isDone = true;
            }
            int dividerPosition1 = in.indexOf(" ");
            int dividerPosition2 = in.indexOf(":");
            tasks.add(new Event(in.substring((dividerPosition1+1),dividerPosition2-3),
                    in.substring(dividerPosition2 + 2,in.length()-1)));
        }
        else {
            int dividerPosition1 = in.indexOf(" ");
            int dividerPosition2 = in.indexOf("/");
            tasks.add(new Event(in.substring((dividerPosition1 + 1), dividerPosition2),
                    in.substring(dividerPosition2 + 4)));
        }
        if (isDone) {
            tasks.get(numOfTasks).setAsDone();
        }
        printTask(tasks, numOfTasks);
        numOfTasks++;
    }

    public static void printTask (ArrayList<Task> tasks, int numOfTasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(numOfTasks).toString());
        System.out.println("Now you have " + (numOfTasks+1) + " task(s) in the list.");
    }
}
