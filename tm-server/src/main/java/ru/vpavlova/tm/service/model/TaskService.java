package ru.vpavlova.tm.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.model.ITaskRepository;
import ru.vpavlova.tm.api.repository.model.IUserRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.model.ITaskService;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.empty.EmptyDescriptionException;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.exception.empty.EmptyUserIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.UserNotFoundException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;
import ru.vpavlova.tm.repository.model.TaskRepository;
import ru.vpavlova.tm.repository.model.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TaskService extends AbstractService<Task> implements ITaskService {

    public TaskService(@NotNull IConnectionService connectionService) {
        super(connectionService);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Task add(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final String description
    ) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        if (description == null || description.isEmpty()) throw new EmptyDescriptionException();
        @NotNull final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
            @NotNull final IUserRepository userRepository = new UserRepository(entityManager);
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
    public void remove(@Nullable Task entity) {
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
    public void add(@Nullable final Task task) {
        if (task == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
    public void addAll(@NotNull List<Task> entities) {
        if (entities == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
    public List<Task> findAll() {
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findAll();
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
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
            taskRepository.removeById(id);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public @NotNull Optional<Task> findById(@NotNull String id) {
        return Optional.empty();
    }

    @Override
    @SneakyThrows
    public void clear(@Nullable final String userId) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
    public List<Task> findAll(@Nullable String userId) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findAllByUserId(userId);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Task> findOneById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findById(id);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Task> findOneByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findOneByIndex(userId, index);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Task> findOneByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findOneByName(userId, name);
    }

    @Override
    @SneakyThrows
    public void remove(
            @Nullable final String userId, @Nullable final Task entity
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
            @NotNull Optional<Task> task = taskRepository.findOneByIndex(userId, index);
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
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
        @NotNull final Optional<Task> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
        @NotNull final Optional<Task> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
        @NotNull final Optional<Task> entity = findOneByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
        @NotNull final Optional<Task> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
        @NotNull final Optional<Task> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
        @NotNull final Optional<Task> entity = findOneByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
        @NotNull final Optional<Task> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
        @NotNull final Optional<Task> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
        @NotNull final Optional<Task> entity = findOneByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
        @NotNull final Optional<Task> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setName(name);
        entity.get().setDescription(description);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
        @NotNull final Optional<Task> entity = findOneByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setName(name);
        entity.get().setDescription(description);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskRepository taskRepository = new TaskRepository(entityManager);
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
