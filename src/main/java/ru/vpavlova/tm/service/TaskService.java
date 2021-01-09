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

}
