package ru.vpavlova.tm.api.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.ProjectGraph;
import ru.vpavlova.tm.enumerated.Status;

import java.util.List;
import java.util.Optional;

public interface IProjectGraphService extends IGraphBusinessService<ProjectGraph> {

    ProjectGraph add(
            @Nullable String userId,
            @Nullable String name,
            @Nullable String description
    );

    @SneakyThrows
    void remove(@Nullable ProjectGraph entity);

    @SneakyThrows
    void clear(@Nullable String userId);

    @NotNull
    @SneakyThrows
    List<ProjectGraph> findAll(@Nullable String userId);

    @SneakyThrows
    @NotNull Optional<ProjectGraph> findById(
            @Nullable String userId, @Nullable String id
    );

    @NotNull
    @SneakyThrows
    Optional<ProjectGraph> findOneByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @NotNull
    @SneakyThrows
    Optional<ProjectGraph> findOneByName(
            @Nullable String userId, @Nullable String name
    );

    @SneakyThrows
    void remove(
            @Nullable String userId, @Nullable ProjectGraph entity
    );

    @SneakyThrows
    void removeOneById(
            @Nullable String userId, @Nullable String id
    );

    @SneakyThrows
    void removeOneByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @SneakyThrows
    void removeOneByName(
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
