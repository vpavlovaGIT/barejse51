package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;

public interface IUserService extends IService<User> {

    User findByLogin(String login);

    User findByEmail(String email);

    boolean isLoginExists(String login);

    boolean isEmailExists(String email);

    User removeUser(User user);

    User removeByLogin(String login);

    User create(String login, String password);

    User create(String login, String password, String email);

    User create(String login, String password, Role role);

    User setPassword(String userId, String password);

    User updateUser(String userId, String firstName, String secondName, String middleName);

}
