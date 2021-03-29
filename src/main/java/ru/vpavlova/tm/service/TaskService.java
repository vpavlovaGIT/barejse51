package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.ITaskService;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.exception.empty.EmptyUserIdException;
import ru.vpavlova.tm.entity.Task;

public class TaskService extends AbstractBusinessService<Task> implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(final ITaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    @Override
    public Task add(final String userId, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        taskRepository.add(userId, task);
        return task;
    }

}
