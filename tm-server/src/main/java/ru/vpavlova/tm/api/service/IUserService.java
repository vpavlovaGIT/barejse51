package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;

public interface IUserService extends IService<User> {

    @NotNull
    User create(@Nullable String login, @Nullable String password);

    @NotNull
    void create(@Nullable String login, @Nullable String password, @Nullable String email);

    @NotNull
    User create(@Nullable String login, @Nullable String password, @Nullable Role role);

    void setPassword(@Nullable String userId, @Nullable String password);

    @NotNull
    User findByLogin(@Nullable String login);

    @Nullable
    User findByEmail(@Nullable String email);

    void updateUser(
            @Nullable String userId,
            @NotNull String firstName,
            @NotNull String lastName,
            @NotNull String middleName
    );

    void removeByLogin(@Nullable String login);

   void lockUserByLogin(@Nullable String login);

    void unlockUserByLogin(@Nullable String login);

}
