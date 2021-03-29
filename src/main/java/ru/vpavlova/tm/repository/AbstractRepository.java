package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    protected final List<E> entities = new ArrayList<>();

    public Predicate<E> predicateById(final String id) {
        return e -> id.equals(e.getId());
    }

    @Override
    public List<E> findAll() {
        return entities;
    }

    @Override
    public E add(final E entity) {
        entities.add(entity);
        return entity;
    }

    @Override
    public Optional<E> findById(final String id) {
        return entities.stream()
                .filter(entity -> id.equals(entity.getId()))
                .findFirst();
    }

    @Override
    public void clear() {
        entities.clear();
    }

    @Override
    public void remove(final E entity) {
        entities.remove(entity);
    }

    @Override
    public E removeById(final String id) {
        final Optional<E> entity = findById(id);
        entity.ifPresent(entities::remove);
        return entity.orElse(null);
    }

}
