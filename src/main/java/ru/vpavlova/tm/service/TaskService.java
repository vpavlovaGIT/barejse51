package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.ITaskRepository;
import ru.vpavlova.tm.api.ITaskService;
import ru.vpavlova.tm.model.Task;
import java.util.List;

public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(final ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task add(final String name, final String description) {
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        taskRepository.add(task);
        return task;
    }

    @Override
    public void add(final Task task) {
        if (task == null) return;
        taskRepository.add(task);
    }

    @Override
    public void remove(final Task task) {
        if (task == null) return;
        taskRepository.remove(task);
    }

    @Override
    public void clear() {
        taskRepository.clear();
    }

    @Override
    public Task findOneById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return taskRepository.findOneById(id);
    }

    @Override
    public Task findOneByIndex(final Integer index) {
        if (index == null || index < 0) return null;
        return taskRepository.findOneByIndex(index);
    }

    @Override
    public Task findOneByName(final String name) {
        if (name == null || name.isEmpty()) return null;
        return taskRepository.findOneByName(name);

    }

    @Override
    public Task removeOneById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return taskRepository.removeOneById(id);
    }

    @Override
    public Task removeOneByIndex(final Integer index) {
        if (index == null || index < 0) return null;
        return taskRepository.removeOneByIndex(index);
    }

    @Override
    public Task removeOneByName(final String name) {
        if (name == null || name.isEmpty()) return null;
        return taskRepository.removeOneByName(name);
    }

    @Override
    public Task updateTaskById(final String id, final String name, final String description) {
        if (id == null || id.isEmpty()) return null;
        if (name == null || name.isEmpty()) return null;
        final Task task = findOneById(id);
        if (task == null) return null;
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task updateTaskByIndex(final Integer index, final String name, final String description) {
        if (index == null || index < 0) return null;
        if (name == null || name.isEmpty()) return null;
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        task.setName(name);
        task.setDescription(description);
        return task;
    }

}
