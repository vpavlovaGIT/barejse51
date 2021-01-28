package ru.vpavlova.tm.enumerated;

import ru.vpavlova.tm.comparator.*;

import java.util.Comparator;

public enum Sort {

    NAME("Sort by name", ComparatorByName.getInstance()),
    CREATED( "Sort by created", ComparatorByCreated.getInstance()),
    DATE_START("Sort by date start", ComparatorByDateStart.getInstance()),
    DATE_FINISH("Sort by date finish", ComparatorByDateFinish.getInstance()),
    STATUS("Sort by status", ComparatorByStatus.getInstance());

    private final String displayName;

    private final Comparator comparator;

    Sort(String displayName, Comparator comparator) {
        this.displayName = displayName;
        this.comparator = comparator;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Comparator getComparator() {
        return comparator;
    }

}
