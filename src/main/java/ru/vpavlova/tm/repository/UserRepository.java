package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private List<User> users = new ArrayList<>();

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User add(final User user) {
        users.add(user);
        return user;
    }

    @Override
    public User findById(final String id) {
        for (final User user: users) {
            if (id.equals(user.getId())) return user;
        }
        return null;
    }

    @Override
    public User findByLogin(final String login) {
        for (final User user: users) {
            if (login.equals(user.getLogin())) return user;
        }
        return null;
    }

    @Override
    public User findByEmail(final String email) {
        for (final User user: users) {
            if (email.equals(user.getEmail())) return user;
        }
        return null;
    }

    @Override
    public User removeUser(final User user) {
        users.remove(user);
        return user;

    }

    @Override
    public User removeById(final String id) {
        final User user = findById(id);
        if (user == null) return null;
        return removeUser(user);
    }

    @Override
    public User removeByLogin(final String login) {
        final User user = findByLogin(login);
        if (user == null) return null;
        return removeUser(user);
    }

}
