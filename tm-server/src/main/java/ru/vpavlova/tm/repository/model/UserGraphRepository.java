package ru.vpavlova.tm.repository.model;

import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.model.IUserGraphRepository;
import ru.vpavlova.tm.entity.UserGraph;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserGraphRepository extends AbstractGraphRepository<UserGraph> implements IUserGraphRepository {

    public UserGraphRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    public List<UserGraph> findAll() {
        return entityManager.createQuery("SELECT e FROM UserGraph e", UserGraph.class).getResultList();
    }

    @Override
    public @NotNull Optional<UserGraph> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(UserGraph.class, id));
    }

    public void clear() {
        findAll().forEach(entityManager::remove);
    }

    public void removeById(@Nullable final String id) {
        @NotNull final UserGraph reference = entityManager.getReference(UserGraph.class, id);
        entityManager.remove(reference);
    }

    @Override
    public @NotNull Optional<UserGraph> findByLogin(@Nullable final String login) {
        return getSingleResult(entityManager
                .createQuery("SELECT e FROM UserGraph e WHERE e.login = :login", UserGraph.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("login", login)
                .setMaxResults(1));
    }

    @Override
    public void removeByLogin(@Nullable final String login) {
        final @NotNull Optional<UserGraph> user = findByLogin(login);
        if (!user.isPresent()) return;
        entityManager.remove(user.get());
    }

}
