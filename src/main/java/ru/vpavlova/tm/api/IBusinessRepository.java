package ru.vpavlova.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.enumerated.Status;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface IBusinessRepository<E extends AbstractBusinessEntity> extends IRepository<E> {

    @NotNull
    List<E> findAll(@NotNull String userId);

    @NotNull
    List<E> findAll(@NotNull String userId, @NotNull Comparator<E> comparator);

    @Nullable
    E add(@NotNull String userId, @NotNull E entity);

    @NotNull
    Optional<E> findById(@NotNull String userId, @NotNull String id);

    @NotNull
    Optional<E> findByIndex(@NotNull String userId, @NotNull Integer index);

    @NotNull
    Optional<E> findByName(@NotNull String userId, @NotNull String name);

    void clear(@NotNull String userId);

    @Nullable
    E removeById(@NotNull String userId, @NotNull String id);

    @Nullable
    E removeByIndex(@NotNull String userId, @NotNull Integer index);

    @Nullable
    E removeByName(@NotNull String userId, @NotNull String name);

    void remove(@NotNull String userId, @NotNull E entity);

}
