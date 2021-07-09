package ru.vpavlova.tm.repository.dto;

import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.dto.ISessionRepository;
import ru.vpavlova.tm.dto.Session;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    public SessionRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    public List<Session> findAll() {
        return entityManager
                .createQuery("SELECT e FROM Session e", Session.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
    }

    @NotNull
    public Optional<Session> findOneById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(Session.class, id));
    }

    public void clear() {
        entityManager
                .createQuery("DELETE FROM Session e")
                .executeUpdate();
    }

    public void removeOneById(@Nullable final String id) {
        Session reference = entityManager.getReference(Session.class, id);
        entityManager.remove(reference);
    }

}
