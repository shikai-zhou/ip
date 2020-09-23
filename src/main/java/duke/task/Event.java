package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate date;
    protected String at;
    protected boolean isDate;
    public Event (String description, String at) {
        super(description);
        try {
            this.date = LocalDate.parse(at);
            isDate = true;
        } catch (DateTimeParseException e) {
            this.at = at;
            isDate = false;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                (isDate?date.format(DateTimeFormatter.ofPattern("d MMM YYYY")):at) + ")";
    }
}
