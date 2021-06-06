package ru.vpavlova.tm.api.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IBusinessService;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.enumerated.Status;

import java.util.Optional;

public interface IProjectService extends IBusinessService<Project> {

    @Nullable
    Project add(
            @Nullable String userId,
            @Nullable String name,
            @Nullable String description
    );

    @SneakyThrows
    void remove(@Nullable Project entity);

    @NotNull
    @SneakyThrows
    Optional<Project> findOneById(
            @Nullable String userId, @Nullable String id
    );

    @NotNull
    @SneakyThrows
    Optional<Project> findByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @NotNull
    @SneakyThrows
    Optional<Project> findByName(
            @Nullable String userId, @Nullable String name
    );

    @SneakyThrows
    void removeById(
            @Nullable String userId, @Nullable String id
    );

    @SneakyThrows
    void removeByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @SneakyThrows
    void removeByName(
            @Nullable String userId, @Nullable String name
    );

    @SneakyThrows
    void changeStatusById(
            @Nullable String userId,
            @Nullable String id,
            @Nullable Status status
    );

    @SneakyThrows
    void updateById(
            @Nullable String userId,
            @Nullable String id,
            @Nullable String name,
            @Nullable String description
    );

}
