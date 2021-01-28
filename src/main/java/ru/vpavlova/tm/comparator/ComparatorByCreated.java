package ru.vpavlova.tm.comparator;

import ru.vpavlova.tm.api.entity.IHasCreated;

import java.util.Comparator;

public final class ComparatorByCreated implements Comparator<IHasCreated> {

    private static final ComparatorByCreated INSTANCE = new ComparatorByCreated();

    private ComparatorByCreated() {
    }

    public static ComparatorByCreated getInstance() {
        return INSTANCE;
    }

    @Override
    public int compare(final IHasCreated o1, final IHasCreated o2) {
        if (o1 == null || o2 == null) return 0;
        return o1.getCreated().compareTo(o2.getCreated());
    }

}
