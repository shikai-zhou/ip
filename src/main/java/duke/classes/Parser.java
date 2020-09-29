package duke.classes;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.UnkownCommandException;
import java.io.IOException;

public class Parser {
    public boolean parse (String input, TaskList tasks) {
        // parses the user input
        Ui ui = new Ui();
        try {
            if (input.equals("bye")) {
                ui.exit(tasks);
                try {
                    tasks.writeToFile("data/tasks.txt");
                } catch (IOException e) {
                    System.out.println("Error: Unable to write to file");
                }
                return true;
            } else if (input.equals("list")) {
                tasks.list();
            } else if (input.matches("done(.*)")) {
                tasks.markDone(input);
            } else if (input.matches("todo.*")||input.startsWith("[T]")) {
                try {
                    tasks.addTodo(input);

                } catch (EmptyDescriptionException e) {
                    System.out.println("OOPS!!! The description of a tasks cannot be empty.");
                }
            } else if (input.matches("deadline.*")||input.startsWith("[D]")) {
                try {
                    tasks.addDeadline(input);

                } catch (EmptyDescriptionException e) {
                    System.out.println("OOPS!!! The description of a tasks cannot be empty.");
                }
            } else if (input.matches("event.*")||input.startsWith("[E]")) {
                try {
                    tasks.addEvent(input);

                } catch (EmptyDescriptionException e) {
                    System.out.println("OOPS!!! The description of a tasks cannot be empty.");
                }
            } else if (input.matches("delete.*")) {
                tasks.deleteTask(input);
            } else if (input.startsWith("find")) {
                tasks.findTask(input);
            } else if (input.startsWith("help")) {
                ui.help();
            } else {
                throw new UnkownCommandException();
            }
        } catch (UnkownCommandException e) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            ui.help();
        }
        return false;
    }
}
