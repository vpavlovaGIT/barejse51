package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.entity.AbstractEntity;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity> implements IService<E> {

    @NotNull
    public final IConnectionService connectionService;

    public AbstractService(@NotNull IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public abstract IRepository<E> getRepository(@NotNull Connection connection);

    @NotNull
    @Override
    @SneakyThrows
    public E add(@Nullable final E entity) {
        if (entity == null) throw new ObjectNotFoundException();
        final Connection connection = connectionService.getConnection();
        try {
            @NotNull final IRepository<E> repository = getRepository(connection);
            repository.add(entity);
            connection.commit();
            return entity;
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<E> findAll() {
        final Connection connection = connectionService.getConnection();
        @NotNull final IRepository<E> repository = getRepository(connection);
        return repository.findAll();
    }

    @NotNull
    @Override
    public Optional<E> findById(@Nullable final String id) {
        if (id.isEmpty()) throw new EmptyIdException();
        final Connection connection = connectionService.getConnection();
        @NotNull final IRepository<E> repository = getRepository(connection);
        return repository.findById(id);
    }

    @Override
    @SneakyThrows
    public void addAll(@Nullable List<E> entities) {
        if (entities == null) throw new ObjectNotFoundException();
        final Connection connection = connectionService.getConnection();
        try {
            final IRepository<E> repository = getRepository(connection);
            repository.addAll(entities);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public void clear() {
        final Connection connection = connectionService.getConnection();
        try {
            final IRepository<E> repository = getRepository(connection);
            repository.clear();
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public E removeById(@Nullable final String id) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final Connection connection = connectionService.getConnection();
        @NotNull final IRepository<E> repository = getRepository(connection);
        try {
            @Nullable final E entity = repository.removeById(id);
            connection.commit();
            return entity;
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public void remove(@Nullable final E entity) {
        if (entity == null) throw new ObjectNotFoundException();
        final Connection connection = connectionService.getConnection();
        try {
            final IRepository<E> repository = getRepository(connection);
            repository.remove(entity);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public boolean contains(@NotNull String id) {
        if (id.isEmpty()) throw new EmptyIdException();
        final Connection connection = connectionService.getConnection();
        @NotNull final IRepository<E> repository = getRepository(connection);
        return repository.contains(id);
    }

}