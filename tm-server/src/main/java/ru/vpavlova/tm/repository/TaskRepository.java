package ru.vpavlova.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.constant.ConstantField;
import ru.vpavlova.tm.constant.TableConst;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.enumerated.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository extends AbstractBusinessRepository<Task> implements ITaskRepository {

    public TaskRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TableConst.TASK;
    }

    @NotNull
    @Override
    @SneakyThrows
    public Task add(@NotNull Task task) {
        @NotNull final String query =
                "INSERT INTO " + getTableName() +
                        "(`id`, `name`, `description`, `user_id`, `created`, " +
                        "`date_start`, `date_finish`, `status`, `project_id`) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, task.getId());
        statement.setString(2, task.getName());
        statement.setString(3, task.getDescription());
        statement.setString(4, task.getUserId());
        statement.setDate(5, prepare(task.getCreated()));
        statement.setDate(6, prepare(task.getDateStart()));
        statement.setDate(7, prepare(task.getDateFinish()));
        statement.setString(8, task.getStatus().toString());
        statement.setString(9, task.getProjectId());
        statement.execute();
        return task;
    }

    @Nullable
    @Override
    @SneakyThrows
    protected Task fetch(@Nullable ResultSet row) {
        if (row == null) return null;
        @NotNull Task task = new Task();
        task.setId(row.getString(ConstantField.ID));
        task.setName(row.getString(ConstantField.NAME));
        task.setDescription(row.getString(ConstantField.DESCRIPTION));
        task.setDateStart(row.getDate(ConstantField.DATE_START));
        task.setDateFinish(row.getDate(ConstantField.DATE_FINISH));
        task.setUserId(row.getString(ConstantField.USER_ID));
        task.setStatus(Status.valueOf(row.getString("STATUS")));
        return task;
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Task> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final String query =
                "SELECT * FROM " + getTableName() +
                        " WHERE `user_id` = ? AND `project_id` = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, projectId);
        @Nullable ResultSet resultSet = statement.executeQuery();
        @Nullable List<Task> result = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) result.add(fetch(resultSet));
        }
        statement.close();
        return result;
    }

    @NotNull
    @Override
    public List<Task> removeAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        if (projectId.isEmpty()) return null;
        @NotNull final Optional<List<Task>> tasks = Optional.ofNullable(findAllByProjectId(userId, projectId));
        tasks.ifPresent(e -> tasks.get().forEach(this::remove));
        return tasks.orElse(null);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Task> bindTaskByProject(
            @NotNull final String userId,
            @NotNull final String projectId,
            @NotNull final String taskId) {
        final Optional<Task> task = findById(userId, taskId);
        if (!task.isPresent()) return Optional.empty();
        task.get().setProjectId(projectId);
        @NotNull final String query =
                "UPDATE " + getTableName() +
                        " SET `project_id` = ?" +
                        " WHERE `user_id` = ? AND `id` = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, projectId);
        statement.setString(2, userId);
        statement.setString(3, taskId);
        statement.execute();
        return task;
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Task> unbindTaskFromProject(@NotNull final String userId, @NotNull final String taskId) {
        @NotNull final Optional<Task> task = findById(userId, taskId);
        if (!task.isPresent()) return Optional.empty();
        task.get().setProjectId(null);
        @NotNull final String query =
                "UPDATE " + getTableName() +
                        " SET `project_id` = NULL" +
                        " WHERE `user_id` = ? AND `id` = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, taskId);
        statement.execute();
        return task;
    }

}
