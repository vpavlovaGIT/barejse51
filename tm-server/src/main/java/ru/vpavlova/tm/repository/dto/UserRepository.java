package ru.vpavlova.tm.repository.dto;

import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.dto.IUserRepository;
import ru.vpavlova.tm.dto.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    public UserRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    public List<User> findAll() {
        return entityManager
                .createQuery("SELECT e FROM User e", User.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
    }

    @NotNull
    @Override
    public Optional<User> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    public void clear() {
        entityManager
                .createQuery("DELETE FROM User e")
                .executeUpdate();
    }

    public void removeOneById(@Nullable final String id) {
        User reference = entityManager.getReference(User.class, id);
        entityManager.remove(reference);
    }

    @NotNull
    @Override
    public Optional<User> findByLogin(@Nullable final String login) {
        return getSingleResult(entityManager
                .createQuery("SELECT e FROM User e WHERE e.login = :login", User.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("login", login)
                .setMaxResults(1));
    }

    @Override
    public void removeByLogin(@Nullable final String login) {
        entityManager
                .createQuery("DELETE FROM User e WHERE e.login = :login")
                .setParameter("login", login)
                .executeUpdate();
    }

}
