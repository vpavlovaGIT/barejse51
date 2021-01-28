package ru.vpavlova.tm.comparator;

import ru.vpavlova.tm.api.entity.IHasDateFinish;

import java.util.Comparator;

public final class ComparatorByDateFinish implements Comparator<IHasDateFinish> {

    private static final ComparatorByDateFinish INSTANCE = new ComparatorByDateFinish();

    private ComparatorByDateFinish() {
    }

    public static ComparatorByDateFinish getInstance() {
        return INSTANCE;
    }

    @Override
    public int compare(final IHasDateFinish o1, final IHasDateFinish o2) {
        if (o1 == null || o2 == null) return 0;
        return o1.getDateFinish().compareTo(o2.getDateFinish());
    }

}
