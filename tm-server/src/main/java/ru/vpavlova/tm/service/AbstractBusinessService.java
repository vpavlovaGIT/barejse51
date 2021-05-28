package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.api.IBusinessService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.exception.empty.EmptyUserIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractBusinessService<E extends AbstractBusinessEntity> extends AbstractService<E> implements IBusinessService<E> {

    public AbstractBusinessService(@NotNull IConnectionService connectionService) {
        super(connectionService);
    }

    public abstract IBusinessRepository<E> getRepository(@NotNull Connection connection);

    @NotNull
    @Override
    public List<E> findAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        final Connection connection = connectionService.getConnection();
        @NotNull final IBusinessRepository<E> repository = getRepository(connection);
        return repository.findAll(userId);
    }

    @Nullable
    @Override
    @SneakyThrows
    public E add(@Nullable final String userId, @Nullable final E entity) {
        if (!Optional.ofNullable(userId).isPresent()) return null;
        if (!Optional.ofNullable(entity).isPresent()) throw new ObjectNotFoundException();
        @NotNull final Connection connection = connectionService.getConnection();
        try {
            @NotNull final IBusinessRepository<E> businessRepository = getRepository(connection);
            @Nullable final E result = businessRepository.add(userId, entity);
            connection.commit();
            return result;
        } catch (final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @NotNull
    @Override
    public Optional<E> findById(@Nullable final String userId, @Nullable final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Connection connection = connectionService.getConnection();
        final IBusinessRepository<E> businessRepository = getRepository(connection);
        return businessRepository.findById(userId, id);
    }

    @NotNull
    @Override
    public Optional<E> findByIndex(@Nullable final String userId, @Nullable Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Connection connection = connectionService.getConnection();
        final IBusinessRepository<E> businessRepository = getRepository(connection);
        return businessRepository.findByIndex(userId, index);
    }

    @NotNull
    @Override
    public Optional<E> findByName(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Connection connection = connectionService.getConnection();
        final IBusinessRepository<E> businessRepository = getRepository(connection);
        return businessRepository.findByName(userId, name);
    }

    @Override
    @SneakyThrows
    public void clear(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        final Connection connection = connectionService.getConnection();
        try {
            final IBusinessRepository<E> businessRepository = getRepository(connection);
            businessRepository.clear(userId);
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
    public void remove(@Nullable final String userId, @Nullable final E entity) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (!Optional.ofNullable(entity).isPresent()) throw new ObjectNotFoundException();
        @NotNull final Connection connection = connectionService.getConnection();
        try {
            @NotNull final IBusinessRepository<E> businessRepository = getRepository(connection);
            businessRepository.remove(userId, entity);
            connection.commit();
        } catch (final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeById(@Nullable final String userId, @Nullable final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Connection connection = connectionService.getConnection();
        try {
            final IBusinessRepository<E> businessRepository = getRepository(connection);
            businessRepository.removeById(id, userId);
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
    public void removeByIndex(@Nullable final String userId, @Nullable final Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Connection connection = connectionService.getConnection();
        try {
            final IBusinessRepository<E> businessRepository = getRepository(connection);
            businessRepository.removeByIndex(userId, index);
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
    public void removeByName(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Connection connection = connectionService.getConnection();
        try {
            final IBusinessRepository<E> businessRepository = getRepository(connection);
            businessRepository.removeByName(name, userId);
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
    public Project updateById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final String name,
            @Nullable final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<E> entity = findById(userId, id);
        if (entity == null) throw new ObjectNotFoundException();
        entity.get().setName(name);
        entity.get().setDescription(description);
        final Connection connection = connectionService.getConnection();
        try {
            final IBusinessRepository<E> businessRepository = getRepository(connection);
            businessRepository.updateById(userId, id, name, description);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return null;
    }

    @NotNull
    @Override
    public Optional<E> updateByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final String name,
            @Nullable final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<E> entity = findByIndex(userId, index);
        entity.ifPresent(e -> {
            e.setName(name);
            e.setDescription(description);
        });
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @NotNull
    @Override
    public Optional<E> startById(@Nullable final String userId, @Nullable String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        @NotNull final Optional<E> entity = findById(userId, id);
        entity.ifPresent(e -> e.setStatus(Status.IN_PROGRESS));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @NotNull
    @Override
    public Optional<E> startByIndex(@Nullable final String userId, @Nullable Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final Optional<E> entity = findByIndex(userId, index);
        entity.ifPresent(e -> e.setStatus(Status.IN_PROGRESS));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @NotNull
    @Override
    public Optional<E> startByName(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        @Nullable final Optional<E> entity = findByName(userId, name);
        entity.ifPresent(e -> e.setStatus(Status.IN_PROGRESS));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @NotNull
    @Override
    public Optional<E> finishById(@Nullable final String userId, @Nullable final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        @NotNull final Optional<E> entity = findById(userId, id);
        entity.ifPresent(e -> e.setStatus(Status.COMPLETE));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;

    }

    @NotNull
    @Override
    public Optional<E> finishByIndex(@Nullable final String userId, @Nullable final Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final Optional<E> entity = findByIndex(userId, index);
        entity.ifPresent(e -> e.setStatus(Status.COMPLETE));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @NotNull
    @Override
    public Optional<E> finishByName(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<E> entity = findByName(userId, name);
        entity.ifPresent(e -> e.setStatus(Status.COMPLETE));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @NotNull
    @Override
    public Optional<E> changeStatusById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        @NotNull final Optional<E> entity = findById(userId, id);
        entity.ifPresent(e -> e.setStatus(status));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @NotNull
    @Override
    public Optional<E> changeStatusByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        @NotNull final Optional<E> entity = findByIndex(userId, index);
        entity.ifPresent(e -> e.setStatus(status));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @NotNull
    @Override
    public Optional<E> changeStatusByName(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<E> entity = findByName(userId, name);
        entity.ifPresent(e -> e.setStatus(status));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;

    }

}
