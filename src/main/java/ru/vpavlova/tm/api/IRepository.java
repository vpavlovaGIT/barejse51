package ru.vpavlova.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface IRepository<E extends AbstractEntity> {

    @NotNull
    List<E> findAll();

    @Nullable
    E add(@Nullable E entity);

    @NotNull
    Optional<E> findById(@NotNull String id);

    void clear();

    @Nullable
    E removeById(@NotNull String id);

    void remove(@Nullable E entity);

}
