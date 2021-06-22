package ru.vpavlova.tm.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.model.ITaskRepository;
import ru.vpavlova.tm.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    public TaskRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @NotNull
    @Override
    public Optional<Task> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(Task.class, id));
    }

    @Override
    public void removeById(@Nullable final String id) {
        @NotNull final Task reference = entityManager.getReference(Task.class, id);
        entityManager.remove(reference);
    }

    @NotNull
    @Override
    public List<Task> findAll() {
        return entityManager.createQuery("SELECT e FROM Task e", Task.class).getResultList();
    }

    @NotNull
    @Override
    public List<Task> findAllByUserId(String userId) {
        return entityManager
                .createQuery("SELECT e FROM Task e WHERE e.user.id = :userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public List<Task> findAllByProjectId(@Nullable final String userId, @Nullable final String projectId) {
        return entityManager
                .createQuery(
                        "SELECT e FROM Task e WHERE e.user.id = :userId AND e.project.id = :projectId",
                        Task.class
                )
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
                        "UPDATE Task e SET e.project.id = :projectId WHERE e.user.id = :userId AND e.id = :id"
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
                        "UPDATE Task e SET e.project.id = NULL WHERE e.user.id = :userId AND e.id = :id"
                )
                .setParameter("userId", userId)
                .setParameter("id", id)
                .executeUpdate();
    }

    @NotNull
    @Override
    public Optional<Task> findOneByIdAndUserId(
            @Nullable final String userId, @Nullable final String id
    ) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM Task e WHERE e.id = :id AND e.user.id = :userId", Task.class
                )
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    @NotNull
    @Override
    public Optional<Task> findOneByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        List<Task> list = entityManager
                .createQuery(
                        "SELECT e FROM Task e WHERE e.name = :name AND e.user.id = :userId",
                        Task.class
                )
                .setParameter("name", name)
                .setParameter("userId", userId)
                .setMaxResults(1).getResultList();
        if (list.isEmpty())
            return Optional.empty();
        else
            return Optional.ofNullable(list.get(0));
    }

    @NotNull
    @Override
    public Optional<Task> findOneByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (index == null) return Optional.empty();
        List<Task> list = entityManager
                .createQuery("SELECT e FROM Task e WHERE e.user.id = :userId", Task.class)
                .setParameter("userId", userId)
                .setFirstResult(index).setMaxResults(1).getResultList();
        if (list.isEmpty())
            return Optional.empty();
        else
            return Optional.ofNullable(list.get(0));
    }

    @Override
    public void removeOneByName(@Nullable final String userId, @Nullable final String name) {
        @NotNull final Optional<Task> task = findOneByName(userId, name);
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
        @NotNull final Optional<Task> task = findOneByIdAndUserId(userId, id);
        if (!task.isPresent()) return;
        entityManager.remove(task.get());
    }

}

