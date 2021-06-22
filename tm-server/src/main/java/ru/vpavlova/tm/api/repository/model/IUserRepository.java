package ru.vpavlova.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends IRepository<User> {

    void clear();

    @NotNull
    List<User> findAll();

    @NotNull
    Optional<User> findByLogin(@NotNull String login);

    @NotNull
    Optional<User> findById(@Nullable String id);

    void removeByLogin(@NotNull String login);

    void removeById(@Nullable String id);

}