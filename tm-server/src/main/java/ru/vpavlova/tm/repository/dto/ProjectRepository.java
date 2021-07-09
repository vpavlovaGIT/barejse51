package ru.vpavlova.tm.repository.dto;

import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.dto.IProjectRepository;
import ru.vpavlova.tm.dto.Project;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    public ProjectRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clearByUserId(@NotNull String userId) {
        entityManager
                .createQuery("DELETE FROM Project e WHERE e.userId = :userId")
                .setParameter("userId", userId).executeUpdate();
    }

    @NotNull
    public Optional<Project> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(Project.class, id));
    }

    @Override
    public Optional<Project> findOneByName(@Nullable final String userId, @Nullable final String name) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM Project e WHERE e.name = :name AND e.userId = :userId",
                        Project.class
                )
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("name", name)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    public void removeOneById(@Nullable final String id) {
        Project reference = entityManager.getReference(Project.class, id);
        entityManager.remove(reference);
    }

    public void remove(@NotNull final Project entity) {
        Project reference = entityManager.getReference(Project.class, entity.getId());
        entityManager.remove(reference);
    }

    @NotNull
    public List<Project> findAll() {
        return entityManager
                .createQuery("SELECT e FROM Project e", Project.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
    }

    @NotNull
    @Override
    public List<Project> findAllByUserId(@Nullable final String userId) {
        return entityManager
                .createQuery("SELECT e FROM Project e WHERE e.userId = :userId", Project.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public Optional<Project> findOneByIdAndUserId(
            @Nullable final String userId,
            @Nullable final String id
    ) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM Project e WHERE e.id = :id AND e.userId = :userId",
                        Project.class
                )
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    @NotNull
    @Override
    public Optional<Project> findOneByIndex(
            @Nullable final String userId,
            @Nullable final Integer index
    ) {
        if (index == null) return Optional.empty();
        return getSingleResult(entityManager
                .createQuery("SELECT e FROM Project e WHERE e.userId = :userId", Project.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("userId", userId)
                .setFirstResult(index).setMaxResults(1));
    }

    @Override
    public void removeOneByName(@Nullable final String userId, @Nullable final String name) {
        entityManager
                .createQuery("DELETE FROM Project e WHERE e.name = :name AND e.userId = :userId")
                .setParameter("userId", userId)
                .setParameter("name", name)
                .executeUpdate();
    }

    public void clear() {
        entityManager
                .createQuery("DELETE FROM Project e")
                .executeUpdate();
    }

    @Override
    public void removeOneByIdAndUserId(@Nullable final String userId, @NotNull final String id) {
        entityManager
                .createQuery("DELETE FROM Project e WHERE e.userId = :userId AND e.id=:id")
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate();
    }

}

