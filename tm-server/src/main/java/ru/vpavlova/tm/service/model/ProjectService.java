package ru.vpavlova.tm.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.model.IProjectRepository;
import ru.vpavlova.tm.api.repository.model.IUserRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.model.IProjectService;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.empty.EmptyDescriptionException;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.exception.empty.EmptyUserIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.UserNotFoundException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;
import ru.vpavlova.tm.repository.model.ProjectRepository;
import ru.vpavlova.tm.repository.model.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public final class ProjectService extends AbstractService<Project> implements IProjectService {

    public ProjectService(@NotNull IConnectionService connectionService) {
        super(connectionService);
    }

    @Override
    @SneakyThrows
    public void add(@Nullable final Project entity) {
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.add(entity);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public Project add(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final String description
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        if (description == null || description.isEmpty()) throw new EmptyDescriptionException();
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final IUserRepository userRepository = new UserRepository(entityManager);
            project.setUser(userRepository.findById(userId).get());
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.add(project);
            entityManager.getTransaction().commit();
            return project;
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void addAll(@Nullable List<Project> entities) {
        if (entities == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            entities.forEach(projectRepository::add);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void clear() {
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.clear();
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void remove(@Nullable final Project entity) {
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.removeById(entity.getId());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findAll() {
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        return projectRepository.findAll();
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Project> findById(
            @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        return projectRepository.findById(id);
    }

    @Override
    @SneakyThrows
    public void removeById(
            @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.removeById(id);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void clear(@Nullable final String userId) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.clearByUserId(userId);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findAll(@Nullable final String userId) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        return projectRepository.findAllByUserId(userId);
    }


    @NotNull
    @Override
    @SneakyThrows
    public Optional<Project> findById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        return projectRepository.findOneByIdAndUserId(userId, id);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Project> findOneByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        return projectRepository.findOneByIndex(userId, index);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Project> findOneByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        return projectRepository.findOneByName(userId, name);
    }

    @Override
    @SneakyThrows
    public void remove(
            @Nullable final String userId, @Nullable final Project entity
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.removeOneByIdAndUserId(userId, entity.getId());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeOneById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.removeOneByIdAndUserId(userId, id);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeOneByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            @NotNull Optional<Project> project = projectRepository.findOneByIndex(userId, index);
            if (!project.isPresent()) throw new UserNotFoundException();
            projectRepository.removeOneByIdAndUserId(userId, project.get().getId());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeOneByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.removeOneByName(userId, name);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void changeStatusById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final Status status
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        if (status == null) throw new ObjectNotFoundException();
        @NotNull final Optional<Project> entity = findById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void changeStatusByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final Status status
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        if (status == null) throw new ObjectNotFoundException();
        @NotNull final Optional<Project> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void changeStatusByName(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final Status status
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        if (status == null) throw new ObjectNotFoundException();
        @NotNull final Optional<Project> entity = findOneByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void finishById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final Optional<Project> entity = findById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void finishByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final Optional<Project> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void finishByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<Project> entity = findOneByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void startById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final Optional<Project> entity = findById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void startByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final Optional<Project> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void startByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) return;
        @NotNull final Optional<Project> entity = findOneByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void updateById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final String name,
            @Nullable final String description
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<Project> entity = findById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setName(name);
        entity.get().setDescription(description);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void updateByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final String name,
            @Nullable final String description
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<Project> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setName(name);
        entity.get().setDescription(description);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
            projectRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

}