package ru.vpavlova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.User;

public interface IUserRepository extends IRepository<User> {

    @NotNull
    User findByLogin(@NotNull String login);

    @Nullable
    User findByEmail(@NotNull String email);

    void removeByLogin(@NotNull String login);

    void setPassword(@NotNull String password, @NotNull String userId);

    void lockUser(@NotNull User user);

    void unlockUser(@NotNull User user);

    void updateUser(@NotNull User user);

}
