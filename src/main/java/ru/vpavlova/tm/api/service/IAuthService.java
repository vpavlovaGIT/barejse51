package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;

import java.util.Optional;

public interface IAuthService {

    @NotNull
    Optional<User> getUser();

    @NotNull
    String getUserId();

    boolean isAuth();

    void checkRole(@Nullable Role... roles);

    void logout();

    void login(@Nullable String login, @Nullable String password);

    void registry(@Nullable String login, @Nullable String password, @Nullable String email);

}
