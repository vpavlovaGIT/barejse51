package ru.vpavlova.tm.comparator;

import ru.vpavlova.tm.api.entity.IHasName;

import java.util.Comparator;

public final class ComparatorByName implements Comparator<IHasName> {

    private static final ComparatorByName INSTANCE = new ComparatorByName();

    private ComparatorByName() {
    }

    public static ComparatorByName getInstance() {
        return INSTANCE;
    }

    @Override
    public int compare(final IHasName o1, final IHasName o2) {
        if (o1 == null || o2 == null) return 0;
        return o1.getName().compareTo(o2.getName());
    }

}
