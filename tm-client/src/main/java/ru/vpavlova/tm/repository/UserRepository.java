package ru.vpavlova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.entity.User;

import java.util.Optional;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @NotNull
    @Override
    public Optional<User> findByLogin(@NotNull final String login) {
        return entities.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }

    @NotNull
    @Override
    public Optional<User> findByEmail(@NotNull final String email) {
        return entities.stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();
    }

    @Nullable
    @Override
    public User removeByLogin(@NotNull final String login) {
        final Optional<User> entity = findByLogin(login);
        entity.ifPresent(this::remove);
        return entity.orElse(null);
    }

}
