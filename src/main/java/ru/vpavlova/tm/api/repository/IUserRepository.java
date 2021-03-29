package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.User;

import java.util.Optional;

public interface IUserRepository extends IRepository<User> {

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    User removeByLogin(String login);

}
