package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Event extends Task {
    protected LocalDate date;
    protected String at;
    protected boolean isDate;
    public Event (String description, String at) {
        super(description);
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MMM-yyyy")
                    // create formatter (use English Locale to parse month names)
                    .toFormatter(Locale.ENGLISH);;
            this.date = LocalDate.parse(at, formatter);
            isDate = true;
        } catch (DateTimeParseException e) {
            this.at = at;
            isDate = false;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                (isDate?date.format(DateTimeFormatter.ofPattern("d MMM yyyy")):at) + ")";
    }
}
