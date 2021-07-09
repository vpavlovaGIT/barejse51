package ru.vpavlova.tm.repository.model;

import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.model.IProjectGraphRepository;
import ru.vpavlova.tm.entity.ProjectGraph;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ProjectGraphRepository extends AbstractGraphRepository<ProjectGraph> implements IProjectGraphRepository {

    public ProjectGraphRepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clearByUserId(@NotNull String userId) {
        findAllByUserId(userId).forEach(entityManager::remove);
    }

    @NotNull
    public Optional<ProjectGraph> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(ProjectGraph.class, id));
    }

    @Override
    public Optional<ProjectGraph> findOneByName(@Nullable final String userId, @Nullable final String name) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM ProjectGraph e WHERE e.name = :name AND e.user.id = :userId",
                        ProjectGraph.class
                )
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("name", name)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    public void removeById(@Nullable final String id) {
        @NotNull final ProjectGraph reference = entityManager.getReference(ProjectGraph.class, id);
        entityManager.remove(reference);
    }

    public void remove(@NotNull final ProjectGraph entity) {
        @NotNull final ProjectGraph reference = entityManager.getReference(ProjectGraph.class, entity.getId());
        entityManager.remove(reference);
    }

    @NotNull
    public List<ProjectGraph> findAll() {
        return entityManager
                .createQuery("SELECT e FROM ProjectGraph e", ProjectGraph.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
    }

    @NotNull
    @Override
    public List<ProjectGraph> findAllByUserId(@Nullable final String userId) {
        return entityManager
                .createQuery("SELECT e FROM ProjectGraph e WHERE e.user.id = :userId", ProjectGraph.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public Optional<ProjectGraph> findOneByIdAndUserId(
            @Nullable final String userId, @Nullable final String id
    ) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM ProjectGraph e WHERE e.id = :id AND e.user.id = :userId",
                        ProjectGraph.class
                )
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    @NotNull
    @Override
    public Optional<ProjectGraph> findOneByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (index == null) return Optional.empty();
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM ProjectGraph e WHERE e.user.id = :userId",
                        ProjectGraph.class
                )
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .setParameter("userId", userId)
                .setFirstResult(index).setMaxResults(1));
    }

    @Override
    public void removeOneByName(@Nullable final String userId, @Nullable final String name) {
        @NotNull Optional<ProjectGraph> project = findOneByName(userId, name);
        if (!project.isPresent()) return;
        entityManager.remove(project.get());
    }

    public void clear() {
        findAll().forEach(entityManager::remove);
    }

    @Override
    public void removeOneByIdAndUserId(@Nullable final String userId, @NotNull final String id) {
        @NotNull final Optional<ProjectGraph> project = findOneByIdAndUserId(userId, id);
        if (!project.isPresent()) return;
        entityManager.remove(project.get());
    }

}
