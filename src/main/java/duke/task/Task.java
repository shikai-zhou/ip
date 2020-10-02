package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        //constructor
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return Y or N symbols
    }

    public String getDescription() {
        return description;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "["+this.getStatusIcon()+"] "+this.getDescription();
    }

}
