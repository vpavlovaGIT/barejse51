package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.entity.AbstractEntity;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;

import java.util.Collection;
import java.util.List;

public abstract class AbstractService<E extends AbstractEntity> implements IService<E> {

    protected final IRepository<E> repository;

    protected AbstractService(IRepository<E> repository) {
        this.repository = repository;
    }

    @Override
    public List<E> findAll(final String userId) {
        return repository.findAll(userId);
    }

    @Override
    public E add(final E entity) {
        if (entity == null) return null;
        return repository.add(entity);
    }

    @Override
    public void addAll(final Collection<E> collection) {
        if (collection == null || collection.isEmpty()) return;
        repository.addAll(collection);
    }

    @Override
    public E findOneById(final String userId, final String id) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return repository.findOneById(userId, id);
    }

    @Override
    public E findOneByIndex(final Integer index) {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return repository.findOneByIndex(index);
    }

    @Override
    public void clear() {
        repository.clear();
    }

    @Override
    public void remove(final String userId, final E entity) {
        if (entity == null) return;
        repository.remove(userId, entity);
    }

    @Override
    public E removeOneById(final String userId, final String id) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return repository.removeOneById(userId, id);
    }

    @Override
    public E removeOneByIndex(final Integer index) {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return repository.removeOneByIndex(index);
    }

}
