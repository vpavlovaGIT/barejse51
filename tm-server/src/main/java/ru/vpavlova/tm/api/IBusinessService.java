package ru.vpavlova.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.enumerated.Status;

import java.util.Optional;

public interface IBusinessService<E extends AbstractBusinessEntity>
        extends IBusinessRepository<E>, IService<E> {

    @Nullable
    E add(@NotNull String userId, @Nullable E entity);

    @NotNull
    Optional<E> findById(@Nullable String userId, @Nullable String id);

    @NotNull
    Optional<E> findByIndex(@Nullable String userId, @Nullable Integer index);

    @NotNull
    Optional<E> findByName(@Nullable String userId, @Nullable String name);

    void clear(@Nullable String userId);

    @NotNull
    Optional<E> updateByIndex(
            @Nullable String userId,
            @Nullable Integer index,
            @Nullable String name,
            @Nullable String description
    );

    Project updateById(
            @Nullable String userId,
            @Nullable String id,
            @Nullable String name,
            @Nullable String description
    );

    @NotNull
    Optional<E> startByIndex(@Nullable String userId, @Nullable Integer index);

    @NotNull
    Optional<E> startById(@Nullable String userId, @Nullable String id);

    @NotNull
    Optional<E> startByName(@Nullable String userId, @Nullable String name);

    @NotNull
    Optional<E> finishByIndex(@Nullable String userId, @Nullable Integer index);

    @NotNull
    Optional<E> finishById(@Nullable String userId, @Nullable String id);

    @NotNull
    Optional<E> finishByName(@Nullable String userId, @Nullable String name);

    @NotNull
    Optional<E> changeStatusByIndex(
            @Nullable String userId,
            @Nullable Integer index,
            @NotNull Status status
    );

    @NotNull
    Optional<E> changeStatusById(
            @Nullable String userId,
            @Nullable String id,
            @NotNull Status status
    );

    @NotNull
    Optional<E> changeStatusByName(
            @Nullable String userId,
            @Nullable String name,
            @NotNull Status status
    );

    void remove(@Nullable String userId, @Nullable E entity);

    void removeById(@Nullable String userId, @Nullable String id);

    void removeByIndex(@NotNull String userId, @Nullable Integer index);

    void removeByName(@NotNull String userId, @Nullable String name);

}
