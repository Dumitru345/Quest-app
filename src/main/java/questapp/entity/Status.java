package questapp.entity;

public enum Status {
    OPEN ("OPEN"),
    PROGRESS ("IN PROGRESS"),
    DONE ("DONE");


    private final String label;

    Status (String label) {
        this.label=label;
    }

    public String getLabel(){
        return label;
    }
}

