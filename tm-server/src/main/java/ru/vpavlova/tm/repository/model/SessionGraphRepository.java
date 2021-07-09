package ru.vpavlova.tm.repository.model;

import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.model.ISessionGraphRepository;
import ru.vpavlova.tm.entity.SessionGraph;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SessionGraphRepository extends AbstractGraphRepository<SessionGraph> implements ISessionGraphRepository {

    public SessionGraphRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    public List<SessionGraph> findAll() {
        return entityManager
                .createQuery("SELECT e FROM SessionGraph e", SessionGraph.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
    }

    public @NotNull Optional<SessionGraph> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(SessionGraph.class, id));
    }

    public void clear() {
        findAll().forEach(entityManager::remove);
    }

    public void removeOneById(@Nullable final String id) {
        SessionGraph reference = entityManager.getReference(SessionGraph.class, id);
        entityManager.remove(reference);
    }

}
