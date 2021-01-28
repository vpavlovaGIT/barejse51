package ru.vpavlova.tm.comparator;

import ru.vpavlova.tm.api.entity.IHasStatus;

import java.util.Comparator;

public final class ComparatorByStatus implements Comparator<IHasStatus> {

    private static final ComparatorByStatus INSTANCE = new ComparatorByStatus();

    private ComparatorByStatus() {
    }

    public static ComparatorByStatus getInstance() {
        return INSTANCE;
    }

    @Override
    public int compare(final IHasStatus o1, final IHasStatus o2) {
        if (o1 == null || o2 == null) return 0;
        return o1.getStatus().compareTo(o2.getStatus());
    }

}
