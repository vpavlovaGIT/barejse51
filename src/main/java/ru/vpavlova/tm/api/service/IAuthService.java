package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;

import java.util.Optional;

public interface IAuthService {

    Optional<User> getUser();

    String getUserId();

    boolean isAuth();

    void checkRole(Role... roles);

    void logout();

    void login(String login, String password);

    void registry(String login, String password, String email);

}
