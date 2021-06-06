package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ITaskService;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.empty.*;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.UserNotFoundException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;

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
        task.setUserId(userId);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.add(task);
            sqlSession.commit();
            return task;
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void add(@Nullable final Task task) {
        if (task == null) throw new ObjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.add(task);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void addAll(@Nullable List<Task> entities) {
        if (entities == null) throw new ObjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            entities.forEach(taskRepository::add);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void clear() {
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.clear();
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Task> findAll() {
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        return taskRepository.findAll();
    }

    @Override
    @SneakyThrows
    public void removeById(
            @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyLoginException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.removeById(id);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Task> findById(
            @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        return taskRepository.findById(id);
    }

    @Override
    @SneakyThrows
    public void remove(@Nullable final Task entity) {
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.removeById(entity.getId());
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void clear(@Nullable final String userId) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.clearByUserId(userId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Task> findById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        return taskRepository.findOneByIdAndUserId(userId, id);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Task> findByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        return taskRepository.findByIndex(userId, index);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Task> findByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        return taskRepository.findByName(userId, name);
    }

    @Override
    @SneakyThrows
    public void remove(
            @Nullable final String userId, @Nullable final Task entity
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.removeOneByIdAndUserId(userId, entity.getId());
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.removeOneByIdAndUserId(userId, id);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            @NotNull Optional<Task> task = taskRepository.findByIndex(userId, index);
            if (!task.isPresent()) throw new UserNotFoundException();
            taskRepository.removeOneByIdAndUserId(userId, task.get().getId());
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.removeByName(userId, name);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
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
        @NotNull final Optional<Task> entity = findById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.changeStatusById(userId, id, status);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @SneakyThrows
    @Override
    public void changeStatusByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final Status status
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        if (status == null) throw new ObjectNotFoundException();
        @NotNull final Optional<Task> entity = findByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.changeStatusById(userId, entity.get().getId(), status);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @SneakyThrows
    @Override
    public void changeStatusByName(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final Status status
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        if (status == null) throw new ObjectNotFoundException();
        @NotNull final Optional<Task> entity = findByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.changeStatusById(userId, entity.get().getId(), status);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void finishById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final Optional<Task> entity = findById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.changeStatusById(userId, id, Status.COMPLETE);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void finishByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final Optional<Task> entity = findByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.changeStatusById(userId, entity.get().getId(), Status.COMPLETE);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void finishByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<Task> entity = findByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.changeStatusById(userId, entity.get().getId(), Status.COMPLETE);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void startById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final Optional<Task> entity = findById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.changeStatusById(userId, id, Status.IN_PROGRESS);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void startByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final Optional<Task> entity = findByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.changeStatusById(userId, entity.get().getId(), Status.IN_PROGRESS);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void startByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<Task> entity = findByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.changeStatusById(userId, entity.get().getId(), Status.IN_PROGRESS);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
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
        @NotNull final Optional<Task> entity = findById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setName(name);
        entity.get().setDescription(description);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.updateById(userId, id, name, description);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
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
        @NotNull final Optional<Task> entity = findByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setName(name);
        entity.get().setDescription(description);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.updateById(userId, entity.get().getId(), name, description);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

}
