package ru.vpavlova.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IGraphRepository;
import ru.vpavlova.tm.entity.UserGraph;

import java.util.List;
import java.util.Optional;

public interface IUserGraphRepository extends IGraphRepository<UserGraph> {

    void clear();

    @NotNull
    List<UserGraph> findAll();

    @NotNull
    Optional<UserGraph> findByLogin(@NotNull String login);

    @NotNull
    Optional<UserGraph> findById(@Nullable String id);

    void removeByLogin(@NotNull String login);

    void removeById(@Nullable String id);

}