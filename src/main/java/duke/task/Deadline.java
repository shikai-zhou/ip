package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate date;
    protected String by;
    protected boolean isDate;
    public Deadline(String description, String by) {
        super(description);
        try {
            this.date = LocalDate.parse(by);
            isDate = true;
        } catch (DateTimeParseException e) {
            this.by = by;
            isDate = false;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                (isDate?date.format(DateTimeFormatter.ofPattern("d MMM YYYY")):by) + ")";
    }
}
