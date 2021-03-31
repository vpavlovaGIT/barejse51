package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.entity.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TaskRepository extends AbstractBusinessRepository<Task> implements ITaskRepository {

    private final List<Task> tasks = new ArrayList<>();

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
        final Optional<List<Task>> tasks = Optional.ofNullable(findAllByProjectId(userId, projectId));
        tasks.ifPresent(e -> tasks.get().forEach(this::remove));
        return tasks.orElse(null);
    }

    @Override
    public Optional<Task> bindTaskByProject(final String userId, final String projectId, final String taskId) {
        final Optional<Task> task = findById(userId, taskId);
        if (!task.isPresent()) return Optional.empty();
        task.get().setProjectId(projectId);
        return task;
    }

    @Override
    public Optional<Task> unbindTaskFromProject(final String userId, final String taskId) {
        final Optional<Task> task = findById(userId, taskId);
        if (!task.isPresent()) return Optional.empty();
        task.get().setProjectId(null);
        return task;
    }

}
