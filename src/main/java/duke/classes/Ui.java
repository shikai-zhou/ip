package duke.classes;

public class Ui {
    public static String logo =
            "`7MM\"\"\"Yb.               `7MM              \n" +
            "  MM    `Yb.               MM              \n" +
            "  MM     `Mb `7MM  `7MM    MM  ,MP'.gP\"Ya  \n" +
            "  MM      MM   MM    MM    MM ;Y  ,M'   Yb \n" +
            "  MM     ,MP   MM    MM    MM;Mm  8M\"\"\"\"\"\" \n" +
            "  MM    ,dP'   MM    MM    MM `Mb.YM.    , \n" +
            ".JMMmmmdP'     `Mbod\"YML..JMML. YA.`Mbmmd' ";
    /**
     * prints welcome
     */
    public void welcome () {
        System.out.println("Hello! I'm ");
        System.out.println(logo);
        System.out.println("What can I do for you?");

    }
    /**
     * writes current tasks to file and exit program
     */
    public void exit (TaskList tasks) {
        tasks.writeToFile("data/tasks.txt");

        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * prints out help comments
     */
    public void help () {
        System.out.println("Here are all the valid commands:");
        System.out.println("\ttodo DESCRIPTION");
        System.out.println("\tevent DESCRIPTION /at TIME");
        System.out.println("\tdeadline DESCRIPTION /by TIME");
        System.out.println("\tlist");
        System.out.println("\tdone INDEX");
        System.out.println("\tfind KEYWORD");
        System.out.println("\tbye");
    }

    public void showLoadingError() {
        System.out.println("Error Loading File");
    }
}
