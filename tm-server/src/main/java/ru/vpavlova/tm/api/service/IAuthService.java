package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.service.dto.IUserService;
import ru.vpavlova.tm.dto.User;
import ru.vpavlova.tm.enumerated.Role;

import java.util.Optional;

public interface IAuthService {

    void checkRoles(@Nullable Role... roles);

    @NotNull
    Optional<User> getUser();

    @NotNull
    String getUserId();

    @NotNull
    IUserService getUserService();

    boolean isAuth();

    void login(@Nullable String login, @Nullable String password);

    void logout();

    void registry(
            @Nullable String login, @Nullable String password, @Nullable String email
    );

}

