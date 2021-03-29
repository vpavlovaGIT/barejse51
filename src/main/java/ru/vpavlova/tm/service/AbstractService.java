package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.entity.AbstractEntity;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity> implements IService<E> {

    protected final IRepository<E> repository;

    protected AbstractService(IRepository<E> repository) {
        this.repository = repository;
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E add(final E entity) {
        if (!Optional.ofNullable(entity).isPresent()) throw new ObjectNotFoundException();
        return repository.add(entity);
    }

    @Override
    public Optional<E> findById(final String id) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return repository.findById(id);
    }

    @Override
    public void clear() {
        repository.clear();
    }

    @Override
    public E removeById(final String id) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return repository.removeById(id);
    }

    @Override
    public void remove(final E entity) {
        if (!Optional.ofNullable(entity).isPresent()) throw new ObjectNotFoundException();
        repository.remove(entity);
    }

}