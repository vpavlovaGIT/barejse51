package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.entity.User;

import java.util.Optional;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public Optional<User> findByLogin(final String login) {
        return entities.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return entities.stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();
    }

    @Override
    public User removeUser(final User user) {
        entities.remove(user);
        return user;
    }

    @Override
    public User removeByLogin(final String login) {
        final Optional<User> entity = findByLogin(login);
        entity.ifPresent(this::remove);
        return entity.orElse(null);
    }

}
