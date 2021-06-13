package ru.vpavlova.tm.api.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IBusinessService;
import ru.vpavlova.tm.dto.ProjectDTO;
import ru.vpavlova.tm.enumerated.Status;

import java.util.Optional;

public interface IProjectService extends IBusinessService<ProjectDTO> {

    @Nullable
    ProjectDTO add(
            @Nullable String userId,
            @Nullable String name,
            @Nullable String description
    );

    @SneakyThrows
    void remove(@Nullable ProjectDTO entity);

    @NotNull
    @SneakyThrows
    Optional<ProjectDTO> findOneById(
            @Nullable String userId, @Nullable String id
    );

    @NotNull
    @SneakyThrows
    Optional<ProjectDTO> findByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @NotNull
    @SneakyThrows
    Optional<ProjectDTO> findByName(
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
