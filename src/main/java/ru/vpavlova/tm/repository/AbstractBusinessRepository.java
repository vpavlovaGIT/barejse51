package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractBusinessRepository<E extends AbstractBusinessEntity> extends AbstractRepository<E> implements IBusinessRepository<E> {

    protected final List<E> entities = new ArrayList<>();

    public Predicate<E> predicateById(final String id) {
        return e -> id.equals(e.getId());
    }

    public Predicate<E> predicateByName(final String name) {
        return e -> name.equals(e.getName());
    }

    public Predicate<E> predicateByUserId(final String userId) {
        return e -> userId.equals(e.getUserId());
    }

    @Override
    public List<E> findAll(final String userId) {
        final List<E> result = new ArrayList<>();
        for (final E entity : entities) {
            if (userId.equals(entity.getUserId())) result.add(entity);
        }
        return result;
    }

    @Override
    public E add(final String userId, final E entity) {
        entity.setUserId(userId);
        entities.add(entity);
        return entity;
    }

    @Override
    public void addAll(final String userId, final Collection<E> collection) {
        if (collection == null) return;
        for (final E entity : collection) {
            entity.setUserId(userId);
        }
        entities.addAll(collection);
    }

    @Override
    public Optional<E> findOneById(final String userId, final String id) {
        return entities.stream()
                .filter(predicateByUserId(userId))
                .filter(predicateById(id))
                .findFirst();
    }

    @Override
    public Optional<E> findOneByIndex(final String userId, final Integer index) {
        return entities.stream()
                .filter(predicateByUserId(userId))
                .skip(index)
                .findFirst();
    }

    @Override
    public Optional<E> findOneByName(final String userId, final String name) {
        return entities.stream()
                .filter(predicateByUserId(userId))
                .filter(predicateByName(name))
                .limit(1)
                .findFirst();
    }

    @Override
    public void clear(final String userId) {
        entities.stream().filter(predicateByUserId(userId))
                .collect(Collectors.toList())
                .forEach(entities::remove);
    }

    @Override
    public E removeOneById(final String userId, final String id) {
        final Optional<E> entity = findOneById(userId, id);
        entity.ifPresent(this::remove);
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity.orElse(null);
    }

    @Override
    public E removeOneByIndex(final String userId, final Integer index) {
        final Optional<E> entity = findOneByIndex(userId, index);
        entity.ifPresent(this::remove);
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity.orElse(null);
    }

    @Override
    public E removeOneByName(final String userId, final String name) {
        final Optional<E> entity = findOneByName(userId, name);
        entity.ifPresent(this::remove);
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity.orElse(null);
    }

    @Override
    public void remove(final String userId, final E entity) {
        if (!userId.equals(entity.getUserId())) return;
        entities.remove(entity);
    }

}
