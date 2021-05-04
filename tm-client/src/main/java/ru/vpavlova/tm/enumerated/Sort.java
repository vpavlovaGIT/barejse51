package ru.vpavlova.tm.enumerated;

import ru.vpavlova.tm.api.entity.*;

import java.util.Comparator;

public enum Sort {

    NAME("Sort by name", Comparator.comparing(IHasName::getName)),
    CREATED("Sort by created", Comparator.comparing(IHasCreated::getCreated)),
    START_DATE("Sort by date start", Comparator.comparing(IHasDateStart::getDateStart)),
    STATUS("Sort by status", Comparator.comparing(IHasStatus::getStatus));

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
