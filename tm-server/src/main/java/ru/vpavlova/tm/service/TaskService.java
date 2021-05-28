package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ITaskService;
import ru.vpavlova.tm.exception.empty.EmptyDescriptionException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.exception.empty.EmptyUserIdException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.repository.TaskRepository;

import java.sql.Connection;

public class TaskService extends AbstractBusinessService<Task> implements ITaskService {

    public TaskService(@NotNull IConnectionService connectionService) {
        super(connectionService);
    }

    @Override
    public IBusinessRepository<Task> getRepository(@NotNull Connection connection) {
        return new TaskRepository(connection);
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
        final Connection connection = connectionService.getConnection();
        try {
            final ITaskRepository taskRepository = new TaskRepository(connection);
            @Nullable final Task taskReturn = taskRepository.add(task);
            connection.commit();
            return taskReturn;
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

}
