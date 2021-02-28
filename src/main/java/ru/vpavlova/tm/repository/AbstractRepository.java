package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    protected final List<E> entities = new ArrayList<>();

    @Override
    public List<E> findAll() {return entities;}

    @Override
    public E add(final E entity) {
        entities.add(entity);
        return entity;
    }

    @Override
    public void addAll(final Collection<E> collection) {
        if (collection == null) return;
        entities.addAll(collection);
    }

    @Override
    public E findOneById(final String id) {
        if (id == null || id.isEmpty()) return null;
        for (final E entity: entities) {
            if (entity == null) continue;
            if (id.equals(entity.getId())) return entity;
        }
        return null;
    }

    @Override
    public E findOneByIndex(final Integer index) {
        return entities.get(index);
    }

    @Override
    public void clear() {
        entities.clear();
    }

    @Override
    public void remove(final E entity) { entities.remove(entity); }

    @Override
    public E removeOneById(final String id) {
        final E entity = findOneById(id);
        if (entity == null) return null;
        entities.remove(entity);
        return entity;
    }

    @Override
    public E removeOneByIndex(final Integer index) {
        final E entity = findOneByIndex(index);
        if (entity == null) return null;
        entities.remove(entity);
        return entity;
    }

}
