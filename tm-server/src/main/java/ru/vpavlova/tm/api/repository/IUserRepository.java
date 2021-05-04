package ru.vpavlova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.User;

import java.util.Optional;

public interface IUserRepository extends IRepository<User> {

    @NotNull
    Optional<User> findByLogin(@NotNull String login);

    @NotNull
    Optional<User> findByEmail(@NotNull String email);

    @Nullable
    User removeByLogin(@NotNull String login);

}
