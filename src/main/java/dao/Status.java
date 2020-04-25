package dao;

public enum Status {
    ACTIVE("active"),
    INACTIVE("inactive");

    private String label;

    private Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
