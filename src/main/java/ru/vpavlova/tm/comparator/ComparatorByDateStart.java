package ru.vpavlova.tm.comparator;

import ru.vpavlova.tm.api.entity.IHasDateStart;

import java.util.Comparator;

public final class ComparatorByDateStart implements Comparator<IHasDateStart> {

    private static final ComparatorByDateStart INSTANCE = new ComparatorByDateStart();

    private ComparatorByDateStart() {
    }

    public static ComparatorByDateStart getInstance() {
        return INSTANCE;
    }

    @Override
    public int compare(final IHasDateStart o1, final IHasDateStart o2) {
        if (o1 == null || o2 == null) return 0;
        return o1.getDateStart().compareTo(o2.getDateStart());
    }

}
