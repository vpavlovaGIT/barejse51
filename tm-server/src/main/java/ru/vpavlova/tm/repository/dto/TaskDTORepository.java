package ru.vpavlova.tm.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.dto.ITaskDTORepository;
import ru.vpavlova.tm.dto.TaskDTO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TaskDTORepository extends AbstractDTORepository<TaskDTO> implements ITaskDTORepository {

    public TaskDTORepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    public @NotNull Optional<TaskDTO> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(TaskDTO.class, id));
    }

    public void remove(final TaskDTO entity) {
        TaskDTO reference = entityManager.getReference(TaskDTO.class, entity.getId());
        entityManager.remove(reference);
    }

    public void removeOneById(@Nullable final String id) {
        TaskDTO reference = entityManager.getReference(TaskDTO.class, id);
        entityManager.remove(reference);
    }

    @NotNull
    public List<TaskDTO> findAll() {
        return entityManager.createQuery("SELECT e FROM TaskDTO e", TaskDTO.class).getResultList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllByUserId(String userId) {
        return entityManager
                .createQuery("SELECT e FROM TaskDTO e WHERE e.userId = :userId", TaskDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllByProjectId(@Nullable final String userId, @Nullable final String projectId) {
        return entityManager
                .createQuery(
                        "SELECT e FROM TaskDTO e WHERE e.userId = :userId AND e.projectId = :projectId",
                        TaskDTO.class
                )
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
                        "DELETE FROM TaskDTO e WHERE e.userId = :userId AND e.projectId = :projectId"
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
                        "UPDATE TaskDTO e SET e.projectId = :projectId WHERE e.userId = :userId AND e.id = :id"
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
                        "UPDATE TaskDTO e SET e.projectId = NULL WHERE e.userId = :userId AND e.id = :id"
                )
                .setParameter("userId", userId)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public @NotNull Optional<TaskDTO> findOneByIdAndUserId(
            @Nullable final String userId, @Nullable final String id
    ) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM TaskDTO e WHERE e.id = :id AND e.userId = :userId", TaskDTO.class
                )
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }


    @Override
    public @NotNull Optional<TaskDTO> findOneByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM TaskDTO e WHERE e.name = :name AND e.userId = :userId",
                        TaskDTO.class
                )
                .setParameter("name", name)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    @Override
    public @NotNull Optional<TaskDTO> findOneByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (index == null) return Optional.empty();
        return getSingleResult(entityManager
                .createQuery("SELECT e FROM TaskDTO e WHERE e.userId = :userId", TaskDTO.class)
                .setParameter("userId", userId)
                .setFirstResult(index).setMaxResults(1));
    }

    @Override
    public void removeOneByName(@Nullable final String userId, @Nullable final String name) {
        entityManager
                .createQuery("DELETE FROM TaskDTO e WHERE e.name = :name AND e.userId = :userId")
                .setParameter("userId", userId)
                .setParameter("name", name)
                .executeUpdate();
    }

    public void clear() {
        entityManager
                .createQuery("DELETE FROM TaskDTO e")
                .executeUpdate();
    }

    @Override
    public void clearByUserId(@Nullable final String userId) {
        entityManager
                .createQuery("DELETE FROM TaskDTO e WHERE e.userId = :userId")
                .setParameter("userId", userId).executeUpdate();
    }

    @Override
    public void removeOneByIdAndUserId(@Nullable final String userId, @Nullable final String id) {
        entityManager
                .createQuery("DELETE FROM TaskDTO e WHERE e.userId = :userId AND e.id =:id")
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate();
    }

}
