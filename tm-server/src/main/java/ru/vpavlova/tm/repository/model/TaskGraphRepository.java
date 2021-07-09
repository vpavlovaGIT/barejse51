package ru.vpavlova.tm.repository.model;

import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.model.ITaskGraphRepository;
import ru.vpavlova.tm.entity.TaskGraph;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TaskGraphRepository extends AbstractGraphRepository<TaskGraph> implements ITaskGraphRepository {

    public TaskGraphRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    @Override
    public Optional<TaskGraph> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(TaskGraph.class, id));
    }

    @Override
    public void removeById(@Nullable final String id) {
        @NotNull final TaskGraph reference = entityManager.getReference(TaskGraph.class, id);
        entityManager.remove(reference);
    }

    @NotNull
    @Override
    public List<TaskGraph> findAll() {
        return entityManager
                .createQuery("SELECT e FROM TaskGraph e", TaskGraph.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
    }

    @NotNull
    @Override
    public List<TaskGraph> findAllByUserId(String userId) {
        return entityManager
                .createQuery("SELECT e FROM TaskGraph e WHERE e.user.id = :userId", TaskGraph.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public List<TaskGraph> findAllByProjectId(@Nullable final String userId, @Nullable final String projectId) {
        return entityManager
                .createQuery(
                        "SELECT e FROM TaskGraph e WHERE e.user.id = :userId AND e.project.id = :projectId",
                        TaskGraph.class
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
        findAllByProjectId(userId, projectId).forEach(entityManager::remove);
    }

    @Override
    public void bindTaskByProjectId(
            @NotNull final String userId,
            @NotNull final String projectId,
            @NotNull final String taskId
    ) {
        entityManager
                .createQuery(
                        "UPDATE TaskGraph e SET e.project.id = :projectId WHERE e.user.id = :userId AND e.id = :id"
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
                        "UPDATE TaskGraph e SET e.project.id = NULL WHERE e.user.id = :userId AND e.id = :id"
                )
                .setParameter("userId", userId)
                .setParameter("id", id)
                .executeUpdate();
    }

    @NotNull
    @Override
    public Optional<TaskGraph> findOneByIdAndUserId(
            @Nullable final String userId, @Nullable final String id
    ) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM TaskGraph e WHERE e.id = :id AND e.user.id = :userId", TaskGraph.class
                )
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    @NotNull
    @Override
    public Optional<TaskGraph> findOneByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM TaskGraph e WHERE e.name = :name AND e.user.id = :userId",
                        TaskGraph.class
                )
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("name", name)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    @NotNull
    @Override
    public Optional<TaskGraph> findOneByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (index == null) return Optional.empty();
        return getSingleResult(entityManager
                .createQuery("SELECT e FROM TaskGraph e WHERE e.user.id = :userId", TaskGraph.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("userId", userId)
                .setFirstResult(index).setMaxResults(1));
    }

    @Override
    public void removeOneByName(@Nullable final String userId, @Nullable final String name) {
        @NotNull final Optional<TaskGraph> task = findOneByName(userId, name);
        if (!task.isPresent()) return;
        entityManager.remove(task.get());
    }

    public void clear() {
        findAll().forEach(entityManager::remove);
    }

    @Override
    public void clearByUserId(@Nullable final String userId) {
        findAllByUserId(userId).forEach(entityManager::remove);
    }

    @Override
    public void removeOneByIdAndUserId(@Nullable final String userId, @Nullable final String id) {
        @NotNull final Optional<TaskGraph> task = findOneByIdAndUserId(userId, id);
        if (!task.isPresent()) return;
        entityManager.remove(task.get());
    }

}

