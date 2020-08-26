public class Task {
    protected String description;
    protected boolean isDone;
    //constructor
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
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
    //...
}
