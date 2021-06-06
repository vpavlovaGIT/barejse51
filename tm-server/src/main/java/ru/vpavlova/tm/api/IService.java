package ru.vpavlova.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface IService<E extends AbstractEntity> {

    void add(@NotNull E entity);

    void addAll(@NotNull List<E> entities);

    void clear();

    @NotNull
    List<E> findAll();

    void removeById(@Nullable String id);

    @NotNull
    Optional<E> findById(@Nullable String id);

}
