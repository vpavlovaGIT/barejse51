package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.entity.User;

import java.util.List;

public interface IUserRepository {

    List<User> findAll();

    User add(User user);

    User findById(String id);

    User findByLogin(String login);

    User findByEmail(String email);

    User removeUser(User user);

    User removeById(String id);

    User removeByLogin(String login);

}
