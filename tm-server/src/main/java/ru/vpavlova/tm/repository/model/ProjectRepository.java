package ru.vpavlova.tm.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.model.IProjectRepository;
import ru.vpavlova.tm.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    public ProjectRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clearByUserId(@NotNull String userId) {
        findAllByUserId(userId).forEach(entityManager::remove);
    }

    public @NotNull Optional<Project> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(Project.class, id));
    }

    @Override
    public Optional<Project> findOneByName(@Nullable final String userId, @Nullable final String name) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM Project e WHERE e.name = :name AND e.user.id = :userId",
                        Project.class
                )
                .setParameter("name", name)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    public void removeById(@Nullable final String id) {
        @NotNull final Project reference = entityManager.getReference(Project.class, id);
        entityManager.remove(reference);
    }

    public void remove(@NotNull final Project entity) {
        @NotNull final Project reference = entityManager.getReference(Project.class, entity.getId());
        entityManager.remove(reference);
    }

    @NotNull
    public List<Project> findAll() {
        return entityManager.createQuery("SELECT e FROM Project e", Project.class).getResultList();
    }

    @NotNull
    @Override
    public List<Project> findAllByUserId(@Nullable final String userId) {
        return entityManager
                .createQuery("SELECT e FROM Project e WHERE e.user.id = :userId", Project.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public Optional<Project> findOneByIdAndUserId(
            @Nullable final String userId, @Nullable final String id
    ) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM Project e WHERE e.id = :id AND e.user.id = :userId", Project.class
                )
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    @Override
    public @NotNull Optional<Project> findOneByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (index == null) return Optional.empty();
        return getSingleResult(entityManager
                .createQuery("SELECT e FROM Project e WHERE e.user.id = :userId", Project.class)
                .setParameter("userId", userId)
                .setFirstResult(index).setMaxResults(1));
    }

    @Override
    public void removeOneByName(@Nullable final String userId, @Nullable final String name) {
        @NotNull Optional<Project> project = findOneByName(userId, name);
        if (!project.isPresent()) return;
        entityManager.remove(project.get());
    }

    public void clear() {
        findAll().forEach(entityManager::remove);
    }

    @Override
    public void removeOneByIdAndUserId(@Nullable final String userId, @NotNull final String id) {
        @NotNull final Optional<Project> project = findOneByIdAndUserId(userId, id);
        if (!project.isPresent()) return;
        entityManager.remove(project.get());
    }

}
