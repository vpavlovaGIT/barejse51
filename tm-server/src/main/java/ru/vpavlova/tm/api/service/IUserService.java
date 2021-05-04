package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;

import java.util.Optional;

public interface IUserService extends IService<User> {

    @NotNull
    User create(@Nullable String login, @Nullable String password);

    @NotNull
    User create(@Nullable String login, @Nullable String password, @Nullable String email);

    @NotNull
    User create(@Nullable String login, @Nullable String password, @Nullable Role role);

    @NotNull
    Optional<User> setPassword(@Nullable String userId, @Nullable String password);

    @NotNull
    Optional<User> findByLogin(@Nullable String login);

    @NotNull
    Optional<User> findByEmail(@Nullable String email);

    boolean isLoginExist(@Nullable String login);

    boolean isEmailExist(@Nullable String email);

    @NotNull
    Optional<User> updateUser(
            @Nullable String userId,
            @NotNull String firstName,
            @NotNull String lastName,
            @NotNull String middleName
    );

    @NotNull
    User removeByLogin(@Nullable String login);

    @NotNull
    Optional<User> lockUserByLogin(@Nullable String login);

    @NotNull
    Optional<User> unlockUserByLogin(@Nullable String login);

}
