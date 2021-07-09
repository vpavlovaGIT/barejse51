package ru.vpavlova.tm.repository.dto;

import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.dto.ITaskRepository;
import ru.vpavlova.tm.dto.Task;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    public TaskRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    public void remove(final Task entity) {
        Task reference = entityManager.getReference(Task.class, entity.getId());
        entityManager.remove(reference);
    }

    public void removeOneById(@Nullable final String id) {
        Task reference = entityManager.getReference(Task.class, id);
        entityManager.remove(reference);
    }

    @NotNull
    public List<Task> findAll() {
        return entityManager
                .createQuery("SELECT e FROM Task e", Task.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
    }

    @NotNull
    @Override
    public List<Task> findAllByUserId(String userId) {
        return entityManager
                .createQuery("SELECT e FROM Task e WHERE e.userId = :userId", Task.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public Optional<Task> findById(@Nullable String id) {
        return Optional.ofNullable(entityManager.find(Task.class, id));
    }

    @NotNull
    @Override
    public List<Task> findAllByProjectId(@Nullable final String userId, @Nullable final String projectId) {
        return entityManager
                .createQuery(
                        "SELECT e FROM Task e WHERE e.userId = :userId AND e.projectId = :projectId",
                        Task.class
                )
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("userId", userId)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    @Override
    public void removeAllByProjectId(
            @Nullable final String userId, @Nullable final String projectId
    ) {
        entityManager
                .createQuery(
                        "DELETE FROM Task e WHERE e.userId = :userId AND e.projectId = :projectId"
                )
                .setParameter("userId", userId)
                .setParameter("projectId", projectId)
                .executeUpdate();
    }

    @Override
    public void bindTaskByProjectId(
            @NotNull final String userId,
            @NotNull final String projectId,
            @NotNull final String taskId
    ) {
        entityManager
                .createQuery(
                        "UPDATE Task e SET e.projectId = :projectId WHERE e.userId = :userId AND e.id = :id"
                )
                .setParameter("userId", userId)
                .setParameter("id", taskId)
                .setParameter("projectId", projectId)
                .executeUpdate();
    }

    @Override
    public void unbindTaskFromProjectId(@NotNull final String userId, @NotNull final String id) {
        entityManager
                .createQuery(
                        "UPDATE Task e SET e.projectId = NULL WHERE e.userId = :userId AND e.id = :id"
                )
                .setParameter("userId", userId)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public @NotNull Optional<Task> findOneByIdAndUserId(
            @Nullable final String userId, @Nullable final String id
    ) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM Task e WHERE e.id = :id AND e.userId = :userId", Task.class
                )
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    @Override
    public @NotNull Optional<Task> findOneByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM Task e WHERE e.name = :name AND e.userId = :userId",
                        Task.class
                )
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("name", name)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    @NotNull
    @Override
    public Optional<Task> findOneByIndex(
            @Nullable final String userId,
            @Nullable final Integer index
    ) {
        if (index == null) return Optional.empty();
        return getSingleResult(entityManager
                .createQuery("SELECT e FROM Task e WHERE e.userId = :userId", Task.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("userId", userId)
                .setFirstResult(index).setMaxResults(1));
    }

    @Override
    public void removeOneByName(@Nullable final String userId, @Nullable final String name) {
        entityManager
                .createQuery("DELETE FROM Task e WHERE e.name = :name AND e.userId = :userId")
                .setParameter("userId", userId)
                .setParameter("name", name)
                .executeUpdate();
    }

    public void clear() {
        entityManager
                .createQuery("DELETE FROM Task e")
                .executeUpdate();
    }

    @Override
    public void clearByUserId(@Nullable final String userId) {
        entityManager
                .createQuery("DELETE FROM Task e WHERE e.userId = :userId")
                .setParameter("userId", userId).executeUpdate();
    }

    @Override
    public void removeOneByIdAndUserId(@Nullable final String userId, @Nullable final String id) {
        entityManager
                .createQuery("DELETE FROM Task e WHERE e.userId = :userId AND e.id =:id")
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate();
    }

}
