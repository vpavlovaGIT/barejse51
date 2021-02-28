package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.User;

public interface IUserRepository extends IRepository<User> {

    User findByLogin(String login);

    User findByEmail(String email);

    User removeUser(User user);

    User removeByLogin(String login);

}
