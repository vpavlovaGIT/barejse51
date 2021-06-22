package ru.vpavlova.tm.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.model.ISessionRepository;
import ru.vpavlova.tm.entity.Session;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    public SessionRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    public List<Session> findAll() {
        return entityManager.createQuery("SELECT e FROM Session e", Session.class).getResultList();
    }

    public @NotNull Optional<Session> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(Session.class, id));
    }

    public void clear() {
        findAll().forEach(entityManager::remove);
    }

    public void removeOneById(@Nullable final String id) {
        Session reference = entityManager.getReference(Session.class, id);
        entityManager.remove(reference);
    }

}
