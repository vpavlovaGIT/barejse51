package ru.vpavlova.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository extends IRepository<Project> {

    void clear();

    void clearByUserId(@NotNull String userId);

    @NotNull
    List<Project> findAll();

    @NotNull
    List<Project> findAllByUserId(@Nullable String userId);

    @NotNull
    Optional<Project> findById(@Nullable String id);

    @NotNull
    Optional<Project> findOneByIdAndUserId(
            @Nullable String userId, @NotNull String id
    );

    @NotNull
    Optional<Project> findOneByIndex(
            @Nullable String userId, @NotNull Integer index
    );

    Optional<Project> findOneByName(
            @Nullable String userId, @NotNull String name
    );

    void removeById(@Nullable String id);

    void removeOneByIdAndUserId(@Nullable String userId, @NotNull String id);

    void removeOneByName(
            @Nullable String userId, @NotNull String name
    );

}
