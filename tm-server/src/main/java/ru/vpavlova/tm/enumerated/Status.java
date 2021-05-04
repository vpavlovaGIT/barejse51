package ru.vpavlova.tm.enumerated;

public enum Status {

    NOT_STARTED("Not started"),
    IN_PROGRESS("In progress"),
    COMPLETE("Complete");

    private String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
