package ru.vpavlova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository extends AbstractBusinessRepository<Task> implements ITaskRepository {

    @NotNull
    private final List<Task> tasks = new ArrayList<>();

    @NotNull
    @Override
    public List<Task> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @Nullable final List<Task> listTasks = new ArrayList<>();
        for (@Nullable final Task task : tasks) {
            if (projectId.equals(task.getProjectId()) && userId.equals(task.getUserId()))
                listTasks.add(task);
        }
        return listTasks;
    }

    @NotNull
    @Override
    public List<Task> removeAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        if (projectId == null || projectId.isEmpty()) return null;
        @NotNull final Optional<List<Task>> tasks = Optional.ofNullable(findAllByProjectId(userId, projectId));
        tasks.ifPresent(e -> tasks.get().forEach(this::remove));
        return tasks.orElse(null);
    }

    @NotNull
    @Override
    public Optional<Task> bindTaskByProject(
            @NotNull final String userId,
            @NotNull final String projectId,
            @NotNull final String taskId) {
        @NotNull final Optional<Task> task = findById(userId, taskId);
        if (!task.isPresent()) return Optional.empty();
        task.get().setProjectId(projectId);
        return task;
    }

    @NotNull
    @Override
    public Optional<Task> unbindTaskFromProject(@NotNull final String userId, @NotNull final String taskId) {
        @NotNull final Optional<Task> task = findById(userId, taskId);
        if (!task.isPresent()) return Optional.empty();
        task.get().setProjectId(null);
        return task;
    }

}
