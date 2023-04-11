package questapp.entity;

public enum Title {
    BRONZE ("BRONZE"),
    SILVER ("SILVER"),
    GOLD ("GOLD"),
    DIAMOND ("DIAMOND");

    private final String label;

    Title (String label) {
        this.label=label;
    }

    public String getLabel(){
        return label;
    }
}
