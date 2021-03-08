package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractBusinessRepository<E extends AbstractBusinessEntity> extends AbstractRepository<E> implements IBusinessRepository<E> {

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
        for (final E entity: collection) {
            entity.setUserId(userId);
        }
        entities.addAll(collection);
    }

    @Override
    public E findOneById(final String userId, final String id) {
        if (id == null || id.isEmpty()) throw new ObjectNotFoundException();
        for (final E entity : entities) {
            if (entity == null) continue;
            if (!userId.equals(entity.getUserId())) continue;
            if (id.equals(entity.getId())) return entity;
        }
        return null;
    }

    @Override
    public E findOneByIndex(final String userId, final Integer index) {
        final List<E> result = findAll(userId);
        return result.get(index);
    }

    @Override
    public E findOneByName(final String userId, final String name) {
        for (final E entity : entities) {
            if (!userId.equals(entity.getUserId())) continue;
            if (name.equals(entity.getName())) return entity;
        }
        return null;
    }

    @Override
    public void clear(final String userId) {
        final List<E> result = findAll(userId);
        this.entities.removeAll(result);
    }

    @Override
    public E removeOneById(final String userId, final String id) {
        final E entity = findOneById(userId, id);
        if (entity == null) throw new ObjectNotFoundException();
        remove(userId, entity);
        return entity;
    }

    @Override
    public E removeOneByIndex(final String userId, final Integer index) {
        final E entity = findOneByIndex(userId, index);
        if (entity == null) throw new ObjectNotFoundException();
        remove(userId, entity);
        return entity;
    }

    @Override
    public E removeOneByName(final String userId, final String name) {
        final E entity = findOneByName(userId, name);
        if (entity == null) throw new ObjectNotFoundException();
        entities.remove(entity);
        return entity;
    }

    @Override
    public void remove(final String userId, final E entity) {
        if (!userId.equals(entity.getUserId())) return;
        entities.remove(entity);
    }

}
