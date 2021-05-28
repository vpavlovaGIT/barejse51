package ru.vpavlova.tm.api;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IBusinessRepository<E extends AbstractBusinessEntity> extends IRepository<E> {

    @Nullable
    E add(@NotNull String userId, @NotNull E entity);

    @NotNull
    List<E> findAll(@Nullable String userId);

    @NotNull
    Optional<E> findById(@NotNull String userId, @NotNull String id);

    @NotNull
    Optional<E> findByIndex(@NotNull String userId, @NotNull Integer index);

    @NotNull
    Optional<E> findByName(@NotNull String userId, @NotNull String name);

    void clear(@NotNull String userId);

    void removeById(@NotNull String userId, @NotNull String id);

    void removeByIndex(@NotNull String userId, @NotNull Integer index);

    void removeByName(@NotNull String userId, @NotNull String name);

    void remove(@NotNull String userId, @NotNull E entity);

    @SneakyThrows
    Project updateById(
            @Nullable String userId,
            @Nullable String id,
            @Nullable String name,
            @Nullable String description
    );

}
