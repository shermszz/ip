public class Deadline extends Task {
    private String dueBy; //Just the month and day, whichever is given

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    public String getDeadline() {
        return this.dueBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueBy + ")";
    }
}
