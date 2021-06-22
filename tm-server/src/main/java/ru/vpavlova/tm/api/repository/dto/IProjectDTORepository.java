package ru.vpavlova.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.ProjectDTO;

import java.util.List;
import java.util.Optional;

public interface IProjectDTORepository extends IRepositoryDTO<ProjectDTO> {

    void clear();

    void clearByUserId(@NotNull String userId);

    @NotNull
    List<ProjectDTO> findAll();

    @NotNull
    List<ProjectDTO> findAllByUserId(@Nullable String userId);

    @NotNull
    Optional<ProjectDTO> findById(@Nullable String id);

    @NotNull
    Optional<ProjectDTO> findOneByIdAndUserId(
            @Nullable String userId, @NotNull String id
    );

    @NotNull
    Optional<ProjectDTO> findOneByIndex(
            @Nullable String userId, @NotNull Integer index
    );

    Optional<ProjectDTO> findOneByName(
            @Nullable String userId, @NotNull String name
    );

    void removeOneById(@Nullable String id);

    void removeOneByIdAndUserId(@Nullable String userId, @NotNull String id);

    void removeOneByName(
            @Nullable String userId, @NotNull String name
    );

}

