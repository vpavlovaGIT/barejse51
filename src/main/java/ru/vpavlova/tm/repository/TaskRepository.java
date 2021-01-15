package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.ITaskRepository;
import ru.vpavlova.tm.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements ITaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public void add(final Task task) {
        tasks.add(task);
    }

    @Override
    public void remove(final Task task) {
        tasks.remove(task);
    }

    @Override
    public void clear() {
        tasks.clear();
    }

    @Override
    public Task findOneById(final String id) {
        for (final Task task : tasks) {
            if (id.equals(task.getId())) return task;
        }
        return null;
    }

    @Override
    public Task findOneByIndex(final Integer index) {
        return tasks.get(index);
    }

    @Override
    public Task findOneByName(final String name) {
        for (final Task task : tasks) {
            if (name.equals(task.getName())) return task;
        }
        return null;
    }

    @Override
    public Task removeOneById(final String id) {
        final Task task = findOneById(id);
        if (task == null) return null;
        tasks.remove(task);
        return task;
    }

    @Override
    public Task removeOneByIndex(final Integer index) {
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        remove(task);
        return task;
    }

    @Override
    public Task removeOneByName(final String name) {
        final Task task = findOneByName(name);
        if (task == null) return null;
        remove(task);
        return task;
    }

}
