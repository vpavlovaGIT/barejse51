package ru.vpavlova.tm.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.model.ITaskGraphRepository;
import ru.vpavlova.tm.api.repository.model.IUserGraphRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.model.ITaskGraphService;
import ru.vpavlova.tm.entity.TaskGraph;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.empty.EmptyDescriptionException;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.exception.empty.EmptyUserIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.UserNotFoundException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;
import ru.vpavlova.tm.repository.model.TaskGraphRepository;
import ru.vpavlova.tm.repository.model.UserGraphRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public final class TaskGraphService extends AbstractGraphService<TaskGraph> implements ITaskGraphService {

    public TaskGraphService(@NotNull IConnectionService connectionService) {
        super(connectionService);
    }

    @NotNull
    @Override
    @SneakyThrows
    public TaskGraph add(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final String description
    ) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        if (description == null || description.isEmpty()) throw new EmptyDescriptionException();
        @NotNull final TaskGraph task = new TaskGraph();
        task.setName(name);
        task.setDescription(description);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
            task.setUser(userRepository.findById(userId).get());
            taskRepository.add(task);
            entityManager.getTransaction().commit();
            return task;
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void remove(@Nullable TaskGraph entity) {
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.removeById(entity.getId());
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
    public void add(@Nullable final TaskGraph task) {
        if (task == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.add(task);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void addAll(@NotNull List<TaskGraph> entities) {
        if (entities == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            entities.forEach(taskRepository::add);
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
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.clear();
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
    public List<TaskGraph> findAll() {
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            return taskRepository.findAll();
        } finally {
            entityManager.close();
        }
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
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.removeById(id);
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
    public Optional<TaskGraph> findById(
            @NotNull String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            return taskRepository.findById(id);
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
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.clearByUserId(userId);
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
    public List<TaskGraph> findAll(@Nullable String userId) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            return taskRepository.findAllByUserId(userId);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<TaskGraph> findOneById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            return taskRepository.findOneByIdAndUserId(userId, id);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<TaskGraph> findOneByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            return taskRepository.findOneByIndex(userId, index);
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<TaskGraph> findOneByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            return taskRepository.findOneByName(userId, name);
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void remove(
            @Nullable final String userId, @Nullable final TaskGraph entity
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.removeOneByIdAndUserId(userId, entity.getId());
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
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.removeOneByIdAndUserId(userId, id);
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
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            @NotNull Optional<TaskGraph> task = taskRepository.findOneByIndex(userId, index);
            if (!task.isPresent()) throw new UserNotFoundException();
            taskRepository.removeOneByIdAndUserId(userId, task.get().getId());
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
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.removeOneByName(userId, name);
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
        @NotNull final Optional<TaskGraph> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.update(entity.get());
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
        @NotNull final Optional<TaskGraph> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.update(entity.get());
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
        @NotNull final Optional<TaskGraph> entity = findOneByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.update(entity.get());
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
        @NotNull final Optional<TaskGraph> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.update(entity.get());
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
        @NotNull final Optional<TaskGraph> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.update(entity.get());
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
        @NotNull final Optional<TaskGraph> entity = findOneByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.update(entity.get());
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
        @NotNull final Optional<TaskGraph> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.update(entity.get());
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
        @NotNull final Optional<TaskGraph> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.update(entity.get());
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
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<TaskGraph> entity = findOneByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.update(entity.get());
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
        @NotNull final Optional<TaskGraph> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setName(name);
        entity.get().setDescription(description);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @SneakyThrows
    @Override
    public void updateByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final String name,
            @Nullable final String description
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<TaskGraph> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setName(name);
        entity.get().setDescription(description);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskGraphRepository taskRepository = new TaskGraphRepository(entityManager);
            taskRepository.update(entity.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

}
