package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.entity.User;

public interface IAuthService {

    User getUser();

    String getUserId();

    boolean isAuth();

    void logout();

    void login(String login, String password);

    void registry(String login, String password, String email);

}
