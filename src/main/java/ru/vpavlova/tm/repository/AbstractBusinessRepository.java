package ru.vpavlova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractBusinessRepository<E extends AbstractBusinessEntity> extends AbstractRepository<E> implements IBusinessRepository<E> {

    protected final List<E> entities = new ArrayList<>();

    @NotNull
    public Predicate<E> predicateById(@NotNull final String id) {
        return e -> id.equals(e.getId());
    }

    @NotNull
    public Predicate<E> predicateByName(@NotNull final String name) {
        return e -> name.equals(e.getName());
    }

    @NotNull
    public Predicate<E> predicateByUserId(@NotNull final String userId) {
        return e -> userId.equals(e.getUserId());
    }

    @NotNull
    @Override
    public List<E> findAll(@NotNull final String userId) {
        final List<E> result = new ArrayList<>();
        for (final E entity : entities) {
            if (userId.equals(entity.getUserId())) result.add(entity);
        }
        return result;
    }

    @NotNull
    @Override
    public List<E> findAll(@NotNull final String userId, @NotNull final Comparator<E> comparator) {
        return entities.stream()
                .filter(predicateByUserId(userId))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Nullable
    @Override
    public E add(@NotNull final String userId, @NotNull final E entity) {
        entity.setUserId(userId);
        entities.add(entity);
        return entity;
    }

    @NotNull
    @Override
    public Optional<E> findById(@NotNull final String userId, @NotNull final String id) {
        return entities.stream()
                .filter(predicateByUserId(userId))
                .filter(predicateById(id))
                .findFirst();
    }

    @NotNull
    @Override
    public Optional<E> findByIndex(@NotNull final String userId, @NotNull final Integer index) {
        return entities.stream()
                .filter(predicateByUserId(userId))
                .skip(index)
                .findFirst();
    }

    @NotNull
    @Override
    public Optional<E> findByName(@NotNull final String userId, @NotNull final String name) {
        return entities.stream()
                .filter(predicateByUserId(userId))
                .filter(predicateByName(name))
                .limit(1)
                .findFirst();
    }

    @Override
    public void clear(@NotNull final String userId) {
        entities.stream().filter(predicateByUserId(userId))
                .collect(Collectors.toList())
                .forEach(entities::remove);
    }

    @Nullable
    @Override
    public E removeById(@NotNull final String userId, @NotNull final String id) {
        final Optional<E> entity = findById(userId, id);
        entity.ifPresent(this::remove);
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity.orElse(null);
    }

    @Nullable
    @Override
    public E removeByIndex(@NotNull final String userId, @NotNull final Integer index) {
        final Optional<E> entity = findByIndex(userId, index);
        entity.ifPresent(this::remove);
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity.orElse(null);
    }

    @Nullable
    @Override
    public E removeByName(@NotNull final String userId, @NotNull final String name) {
        final Optional<E> entity = findByName(userId, name);
        entity.ifPresent(this::remove);
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity.orElse(null);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final E entity) {
        if (!userId.equals(entity.getUserId())) return;
        entities.remove(entity);
    }

}
