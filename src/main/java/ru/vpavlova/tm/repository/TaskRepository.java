package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.entity.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskRepository implements ITaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public List<Task> findAll(Comparator<Task> comparator) {
        final List<Task> taskList = new ArrayList<>(tasks);
        taskList.sort(comparator);
        return taskList;
    }

    @Override
    public List<Task> findAllByProjectId(final String projectId) {
        final List<Task> listTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getProjectId() == projectId) listTasks.add(task);
        }
        return listTasks;
    }

    @Override
    public List<Task> removeAllByProjectId(final String projectId) {
        if (projectId == null || projectId.isEmpty()) return null;
        for (Task task : tasks) {
            if (task.getProjectId().equals(projectId)) task.setProjectId(null);
        }
        return tasks;
    }

    @Override
    public Task bindTaskByProject(final String projectId, final String taskId) {
        final Task task = findOneById(taskId);
        if (task == null) return null;
        task.setProjectId(projectId);
        return task;
    }

    @Override
    public Task unbindTaskFromProject(final String taskId) {
        final Task task = findOneById(taskId);
        if (task == null) return null;
        task.setProjectId(null);
        return task;
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
