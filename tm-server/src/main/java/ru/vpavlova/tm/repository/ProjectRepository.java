package ru.vpavlova.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.constant.ConstantField;
import ru.vpavlova.tm.constant.TableConst;
import ru.vpavlova.tm.entity.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository extends AbstractBusinessRepository<Project> implements IProjectRepository {

    public ProjectRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TableConst.PROJECT;
    }

    @Override
    @Nullable
    @SneakyThrows
    public Project add(@Nullable Project project) {
        @NotNull final String query =
                "INSERT INTO " + getTableName() +
                        "(`id`, `name`, `description`, `user_id`, `created`, " +
                        "`date_start`, `date_finish`, `status`) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, project.getId());
        statement.setString(2, project.getName());
        statement.setString(3, project.getDescription());
        statement.setString(4, project.getUserId());
        statement.setDate(5, prepare(project.getCreated()));
        statement.setDate(6, prepare(project.getDateStart()));
        statement.setDate(7, prepare(project.getDateFinish()));
        statement.setString(8, project.getStatus().toString());
        statement.execute();
        return project;
    }

    @Nullable
    @SneakyThrows
    protected Project fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull Project project = new Project();
        project.setId(row.getString(ConstantField.ID));
        project.setName(row.getString(ConstantField.NAME));
        project.setDescription(row.getString(ConstantField.DESCRIPTION));
        project.setDateStart(row.getDate(ConstantField.DATE_START));
        project.setDateFinish(row.getDate(ConstantField.DATE_FINISH));
        project.setUserId(row.getString(ConstantField.USER_ID));
        return project;
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findAll(@Nullable final String userId) {
        if (userId.isEmpty()) return new ArrayList<>();
        @NotNull final String query = "SELECT * FROM `app_project` WHERE `user_id` = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        @NotNull ResultSet resultSet = statement.executeQuery();
        @NotNull List<Project> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

}
