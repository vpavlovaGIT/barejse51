package ru.vpavlova.tm.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.dto.IProjectDTORepository;
import ru.vpavlova.tm.dto.ProjectDTO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ProjectDTORepository extends AbstractDTORepository<ProjectDTO> implements IProjectDTORepository {

    public ProjectDTORepository(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void clearByUserId(@NotNull String userId) {
        entityManager
                .createQuery("DELETE FROM ProjectDTO e WHERE e.userId = :userId")
                .setParameter("userId", userId).executeUpdate();
    }

    public @NotNull Optional<ProjectDTO> findById(@Nullable final String id) {
        return Optional.ofNullable(entityManager.find(ProjectDTO.class, id));
    }

    @Override
    public Optional<ProjectDTO> findOneByName(@Nullable final String userId, @Nullable final String name) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM ProjectDTO e WHERE e.name = :name AND e.userId = :userId",
                        ProjectDTO.class
                )
                .setParameter("name", name)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    public void removeOneById(@Nullable final String id) {
        ProjectDTO reference = entityManager.getReference(ProjectDTO.class, id);
        entityManager.remove(reference);
    }

    public void remove(@NotNull final ProjectDTO entity) {
        ProjectDTO reference = entityManager.getReference(ProjectDTO.class, entity.getId());
        entityManager.remove(reference);
    }

    @NotNull
    public List<ProjectDTO> findAll() {
        return entityManager.createQuery("SELECT e FROM ProjectDTO e", ProjectDTO.class).getResultList();
    }

    @NotNull
    @Override
    public List<ProjectDTO> findAllByUserId(@Nullable final String userId) {
        return entityManager
                .createQuery("SELECT e FROM ProjectDTO e WHERE e.userId = :userId", ProjectDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @NotNull
    @Override
    public Optional<ProjectDTO> findOneByIdAndUserId(
            @Nullable final String userId, @Nullable final String id
    ) {
        return getSingleResult(entityManager
                .createQuery(
                        "SELECT e FROM ProjectDTO e WHERE e.id = :id AND e.userId = :userId", ProjectDTO.class
                )
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setMaxResults(1));
    }

    @Override
    public @NotNull Optional<ProjectDTO> findOneByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (index == null) return Optional.empty();
        return getSingleResult(entityManager
                .createQuery("SELECT e FROM ProjectDTO e WHERE e.userId = :userId", ProjectDTO.class)
                .setParameter("userId", userId)
                .setFirstResult(index).setMaxResults(1));
    }

    @Override
    public void removeOneByName(@Nullable final String userId, @Nullable final String name) {
        entityManager
                .createQuery("DELETE FROM ProjectDTO e WHERE e.name = :name AND e.userId = :userId")
                .setParameter("userId", userId)
                .setParameter("name", name)
                .executeUpdate();
    }

    public void clear() {
        entityManager
                .createQuery("DELETE FROM ProjectDTO e")
                .executeUpdate();
    }

    @Override
    public void removeOneByIdAndUserId(@Nullable final String userId, @NotNull final String id) {
        entityManager
                .createQuery("DELETE FROM ProjectDTO e WHERE e.userId = :userId AND e.id=:id")
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate();
    }

}

