package ru.vpavlova.tm.api.service.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.ProjectDTO;
import ru.vpavlova.tm.enumerated.Status;

import java.util.List;
import java.util.Optional;

public interface IProjectDTOService extends IBusinessDTOService<ProjectDTO> {

    ProjectDTO add(
            @Nullable String userId,
            @Nullable String name,
            @Nullable String description
    );

    @SneakyThrows
    void remove(@Nullable ProjectDTO entity);

    @SneakyThrows
    void clear(@Nullable String userId);

    @NotNull
    @SneakyThrows
    List<ProjectDTO> findAll(@Nullable String userId);

    @SneakyThrows
    @NotNull Optional<ProjectDTO> findOneById(
            @Nullable String userId, @Nullable String id
    );

    @NotNull
    @SneakyThrows
    Optional<ProjectDTO> findOneByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @NotNull
    @SneakyThrows
    Optional<ProjectDTO> findOneByName(
            @Nullable String userId, @Nullable String name
    );

    @SneakyThrows
    void remove(
            @Nullable String userId, @Nullable ProjectDTO entity
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
