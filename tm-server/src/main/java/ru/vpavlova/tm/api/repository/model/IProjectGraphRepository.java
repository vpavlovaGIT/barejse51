package ru.vpavlova.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IGraphRepository;
import ru.vpavlova.tm.entity.ProjectGraph;

import java.util.List;
import java.util.Optional;

public interface IProjectGraphRepository extends IGraphRepository<ProjectGraph> {

    void clear();

    void clearByUserId(@NotNull String userId);

    @NotNull
    List<ProjectGraph> findAll();

    @NotNull
    List<ProjectGraph> findAllByUserId(@Nullable String userId);

    @NotNull
    Optional<ProjectGraph> findById(@Nullable String id);

    @NotNull
    Optional<ProjectGraph> findOneByIdAndUserId(
            @Nullable String userId, @NotNull String id
    );

    @NotNull
    Optional<ProjectGraph> findOneByIndex(
            @Nullable String userId, @NotNull Integer index
    );

    Optional<ProjectGraph> findOneByName(
            @Nullable String userId, @NotNull String name
    );

    void removeById(@Nullable String id);

    void removeOneByIdAndUserId(@Nullable String userId, @NotNull String id);

    void removeOneByName(
            @Nullable String userId, @NotNull String name
    );

}
