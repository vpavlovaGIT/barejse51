package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.ITaskService;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;
import ru.vpavlova.tm.entity.Task;

import java.util.Comparator;
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
    public List<Task> findAll(Comparator<Task> comparator) {
        if (comparator == null) return null;
        return taskRepository.findAll(comparator);
    }

    @Override
    public Task add(final String name, final String description) {
        if (name == null || name.isEmpty()) throw new EmptyNameException();
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
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return taskRepository.findOneById(id);
    }

    @Override
    public Task findOneByIndex(final Integer index) {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return taskRepository.findOneByIndex(index);
    }

    @Override
    public Task findOneByName(final String name) {
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        return taskRepository.findOneByName(name);

    }

    @Override
    public Task removeOneById(final String id) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return taskRepository.removeOneById(id);
    }

    @Override
    public Task removeOneByIndex(final Integer index) {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return taskRepository.removeOneByIndex(index);
    }

    @Override
    public Task removeOneByName(final String name) {
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        return taskRepository.removeOneByName(name);
    }

    @Override
    public Task updateTaskById(final String id, final String name, final String description) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Task task = findOneById(id);
        if (task == null) return null;
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task updateTaskByIndex(final Integer index, final String name, final String description) {
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task startProjectById(final String id) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Task task = findOneById(id);
        if (task == null) return null;
        task.setStatus(Status.IN_PROGRESS);
        return task;
    }

    @Override
    public Task startProjectByIndex(final Integer index) {
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        task.setStatus(Status.IN_PROGRESS);
        return task;
    }

    @Override
    public Task startProjectByName(final String name) {
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Task task = findOneByName(name);
        if (task == null) return null;
        task.setStatus(Status.IN_PROGRESS);
        return task;
    }

    @Override
    public Task finishProjectById(final String id) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Task task = findOneById(id);
        if (task == null) return null;
        task.setStatus(Status.COMPLETE);
        return task;
    }

    @Override
    public Task finishProjectByIndex(final Integer index) {
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        task.setStatus(Status.COMPLETE);
        return task;
    }

    @Override
    public Task finishProjectByName(final String name) {
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Task task = findOneByName(name);
        if (task == null) return null;
        task.setStatus(Status.COMPLETE);
        return task;
    }

    @Override
    public Task changeProjectStatusById(final String id, final Status status) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Task task = findOneById(id);
        if (task == null) return null;
        task.setStatus(status);
        return task;
    }

    @Override
    public Task changeProjectStatusByIndex(final Integer index, final Status status) {
        if (index == null) throw new IndexIncorrectException();
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        task.setStatus(status);
        return task;
    }

    @Override
    public Task changeProjectStatusByName(final String name, final Status status) {
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Task task = findOneByName(name);
        if (task == null) return null;
        task.setStatus(status);
        return task;
    }

}
