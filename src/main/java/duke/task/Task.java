package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        //constructor
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     *
     * @return Y/N
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return Y or N symbols
    }

    /**
     *
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     *  set isDOne as true
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     *
     * @return task as String
     */
    public String toString() {
        return "["+this.getStatusIcon()+"] "+this.getDescription();
    }

}
