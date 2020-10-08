package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDate date;
    protected String by;
    protected boolean isDate;

    /**
     * Creates a deadline task
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        // create formatter (use English Locale to parse month names)
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MMM-yyyy")
                .toFormatter(Locale.ENGLISH);
        try {
            this.date = LocalDate.parse(by, formatter);
            isDate = true;
        } catch (DateTimeParseException e) {
            this.by = by;
            isDate = false;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                (isDate?date.format(DateTimeFormatter.ofPattern("d MMM yyyy")):by) + ")";
    }
}
