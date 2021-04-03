package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.api.IBusinessService;
import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.exception.empty.EmptyUserIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public abstract class AbstractBusinessService<E extends AbstractBusinessEntity> extends AbstractService<E> implements IBusinessService<E> {

    @NotNull
    private final IBusinessRepository<E> repository;

    public AbstractBusinessService(@NotNull IBusinessRepository<E> repository) {
        super(repository);
        this.repository = repository;
    }

    @NotNull
    @Override
    public List<E> findAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        return repository.findAll(userId);
    }

    @NotNull
    @Override
    public List<E> findAll(@Nullable final String userId, @Nullable final Comparator<E> comparator) {
        if (!Optional.ofNullable(comparator).isPresent()) return null;
        return repository.findAll(userId, comparator);
    }

    @Nullable
    @Override
    public E add(@Nullable final String userId, @Nullable final E entity) {
        if (!Optional.ofNullable(userId).isPresent()) return null;
        if (!Optional.ofNullable(entity).isPresent()) throw new ObjectNotFoundException();
        return repository.add(userId, entity);
    }

    @NotNull
    @Override
    public Optional<E> findById(@Nullable final String userId, @Nullable final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return repository.findById(userId, id);
    }

    @NotNull
    @Override
    public Optional<E> findByIndex(@Nullable final String userId, @Nullable Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        return repository.findByIndex(userId, index);
    }

    @NotNull
    @Override
    public Optional<E> findByName(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        return repository.findByName(userId, name);
    }

    @Override
    public void clear(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        repository.clear(userId);
    }

    @Override
    public void remove(@Nullable final String userId, @Nullable final E entity) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (!Optional.ofNullable(entity).isPresent()) throw new ObjectNotFoundException();
        repository.remove(userId, entity);
    }

    @Nullable
    @Override
    public E removeById(@Nullable final String userId, @Nullable final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return repository.removeById(userId, id);
    }

    @Nullable
    @Override
    public E removeByIndex(@Nullable final String userId, @Nullable final Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        return repository.removeByIndex(userId, index);
    }

    @Nullable
    @Override
    public E removeByName(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        return repository.removeByName(userId, name);
    }

    @NotNull
    @Override
    public Optional<E> updateById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final String name,
            @Nullable final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<E> entity = findById(userId, id);
        entity.ifPresent(e -> {
            e.setId(id);
            e.setName(name);
            e.setDescription(description);
        });
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
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
