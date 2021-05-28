package ru.vpavlova.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface IRepository<E extends AbstractEntity> {

    @Nullable
    E add(@Nullable E entity);

    void addAll(@NotNull List<E> entity);

    @NotNull
    Optional<E> findById(@NotNull String id);

    @NotNull
    List<E> findAll();

    void clear();

    @Nullable
    E removeById(@NotNull String id);

    void remove(@Nullable E entity);

    boolean contains(@NotNull String id);

}
