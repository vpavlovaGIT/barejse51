package ru.vpavlova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    @NotNull
    protected final List<E> entities = new ArrayList<>();

    @NotNull
    public Predicate<E> predicateById(@NotNull final String id) {
        return e -> id.equals(e.getId());
    }

    @NotNull
    @Override
    public List<E> findAll() {
        return entities;
    }

    @Nullable
    @Override
    public E add(@Nullable final E entity) {
        entities.add(entity);
        return entity;
    }

    @Override
    public void addAll(@NotNull final List<E> entities) {
        entities.addAll(entities);
    }

    @NotNull
    @Override
    public Optional<E> findById(@NotNull final String id) {
        return entities.stream()
                .filter(entity -> id.equals(entity.getId()))
                .findFirst();
    }

    @Override
    public void clear() {
        entities.clear();
    }

    @Override
    public void remove(@Nullable final E entity) {
        entities.remove(entity);
    }

    @Nullable
    @Override
    public E removeById(@NotNull final String id) {
        final Optional<E> entity = findById(id);
        entity.ifPresent(entities::remove);
        return entity.orElse(null);
    }

}
