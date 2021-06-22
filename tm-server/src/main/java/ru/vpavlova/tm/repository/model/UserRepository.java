package ru.vpavlova.tm.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.model.IUserRepository;
import ru.vpavlova.tm.entity.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    public UserRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    public List<User> findAll() {
        return entityManager.createQuery("SELECT e FROM User e", User.class).getResultList();
    }

    @Override
    public @NotNull Optional<User> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    public void clear() {
        findAll().forEach(entityManager::remove);
    }

    public void removeById(@Nullable final String id) {
        @NotNull final User reference = entityManager.getReference(User.class, id);
        entityManager.remove(reference);
    }

    @Override
    public @NotNull Optional<User> findByLogin(@Nullable final String login) {
        List<User> list = entityManager
                .createQuery("SELECT e FROM User e WHERE e.login = :login", User.class)
                .setParameter("login", login)
                .setMaxResults(1).getResultList();
        if (list.isEmpty())
            return Optional.empty();
        else
            return Optional.ofNullable(list.get(0));
    }

    @Override
    public void removeByLogin(@Nullable final String login) {
        final @NotNull Optional<User> user = findByLogin(login);
        if (!user.isPresent()) return;
        entityManager.remove(user.get());
    }

}
