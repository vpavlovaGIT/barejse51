package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.entity.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> findAll(Comparator<Task> comparator) {
        final List<Task> taskList = new ArrayList<>(tasks);
        taskList.sort(comparator);
        return taskList;
    }

    @Override
    public List<Task> findAllByProjectId(final String userId, final String projectId) {
        final List<Task> listTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (projectId.equals(task.getProjectId()) && userId.equals(task.getUserId()))
                listTasks.add(task);
        }
        return listTasks;
    }

    @Override
    public List<Task> removeAllByProjectId(final String userId, final String projectId) {
        if (projectId == null || projectId.isEmpty()) return null;
        for (Task task : tasks) {
            if (task.getProjectId().equals(projectId) && userId.equals(task.getUserId())) task.setProjectId(null);
        }
        return tasks;
    }

    @Override
    public Task bindTaskByProject(final String userId, final String projectId, final String taskId) {
        final Task task = findOneById(userId, taskId);
        if (task == null) return null;
        task.setProjectId(projectId);
        return task;
    }

    @Override
    public Task unbindTaskFromProject(final String userId, final String taskId) {
        final Task task = findOneById(userId, taskId);
        if (task == null) return null;
        task.setProjectId(null);
        return task;
    }

    @Override
    public Task findOneByName(final String userId, final String name) {
        for (final Task task : tasks) {
            if (name.equals(task.getName()) && userId.equals(task.getUserId())) return task;
        }
        return null;
    }

    @Override
    public Task removeOneByName(final String userId, final String name) {
        final Task task = findOneByName(userId, name);
        if (task == null) return null;
        remove(userId, task);
        return task;
    }

}
