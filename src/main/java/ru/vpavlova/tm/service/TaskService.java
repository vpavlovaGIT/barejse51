package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.ITaskService;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.entity.Task;

import java.util.Comparator;
import java.util.List;

public class TaskService extends AbstractBusinessService<Task> implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(final ITaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll(Comparator<Task> comparator) {
        if (comparator == null) return null;
        return taskRepository.findAll(comparator);
    }

    @Override
    public Task add(final String userId, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        if (description == null || description.isEmpty()) return null;
        final Task task = new Task();
        task.setUserId(userId);
        task.setName(name);
        task.setDescription(description);
        taskRepository.add(task);
        return task;
    }

}
