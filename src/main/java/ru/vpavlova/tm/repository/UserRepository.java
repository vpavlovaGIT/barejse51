package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.entity.User;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User findByLogin(final String login) {
        for (final User user: entities) {
            if (login.equals(user.getLogin())) return user;
        }
        return null;
    }

    @Override
    public User findByEmail(final String email) {
        for (final User user: entities) {
            if (email.equals(user.getEmail())) return user;
        }
        return null;
    }

    @Override
    public User removeUser(final User user) {
        entities.remove(user);
        return user;
    }

    @Override
    public User removeByLogin(final String login) {
        final User user = findByLogin(login);
        if (user == null) return null;
        return removeUser(user);
    }

}
