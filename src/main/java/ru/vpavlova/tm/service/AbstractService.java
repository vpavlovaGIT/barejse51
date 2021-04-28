package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.entity.AbstractEntity;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity> implements IService<E> {

    @NotNull
    protected final IRepository<E> repository;

    protected AbstractService(@NotNull IRepository<E> repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E add(@Nullable final E entity) {
        if (!Optional.ofNullable(entity).isPresent()) throw new ObjectNotFoundException();
        return repository.add(entity);
    }

    @NotNull
    @Override
    public Optional<E> findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return repository.findById(id);
    }

    @SneakyThrows
    @Override
    public void addAll(@Nullable List<E> entities) {
        if (entities == null) throw new ObjectNotFoundException();
        repository.addAll(entities);
    }

    @Override
    public void clear() {
        repository.clear();
    }

    @Nullable
    @Override
    public E removeById(@Nullable final String id) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return repository.removeById(id);
    }

    @Override
    public void remove(@Nullable final E entity) {
        if (!Optional.ofNullable(entity).isPresent()) throw new ObjectNotFoundException();
        repository.remove(entity);
    }

    @Override
    public boolean contains(@NotNull String id) {
        return repository.contains(id);
    }

}