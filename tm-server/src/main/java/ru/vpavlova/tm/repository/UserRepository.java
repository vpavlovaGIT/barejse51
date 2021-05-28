package ru.vpavlova.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.constant.ConstantField;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    public UserRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "APP_USER";
    }

    @Override
    @Nullable
    @SneakyThrows
    public User add(@Nullable final User user) {
        if (user == null) return null;
        @NotNull final String query =
                "INSERT INTO `app_user`(`id`, `login`, `lock`, `password_hash`, `email`, " +
                        "`first_name`, `last_name`, `middle_name`, `role`) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, user.getId());
        statement.setString(2, user.getLogin());
        statement.setBoolean(3, user.isLocked());
        statement.setString(4, user.getPasswordHash());
        statement.setString(5, user.getEmail());
        statement.setString(6, user.getFirstName());
        statement.setString(7, user.getLastName());
        statement.setString(8, user.getMiddleName());
        @Nullable Role role = user.getRole();
        if (role != null) statement.setString(9, role.toString());
        else statement.setString(9, "");
        statement.execute();
        return user;
    }

    @Nullable
    @SneakyThrows
    public User fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull User user = new User();
        user.setId(row.getString(ConstantField.ID));
        user.setLogin(row.getString(ConstantField.LOGIN));
        user.setPasswordHash(row.getString(ConstantField.PASSWORD_HASH));
        user.setEmail(row.getString(ConstantField.EMAIL));
        user.setFirstName(row.getString(ConstantField.FIRST_NAME));
        user.setLastName(row.getString(ConstantField.LAST_NAME));
        user.setMiddleName(row.getString(ConstantField.MIDDLE_NAME));
        user.setRole(Role.valueOf(row.getString(ConstantField.ROLE)));
        user.setLocked(row.getBoolean(ConstantField.LOCK));
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public User findByLogin(@NotNull final String login) {
        @NotNull final String query = "SELECT * FROM `app_user` WHERE `login` = ? LIMIT 1";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, login);
        @NotNull ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return null;
        @Nullable User user = fetch(resultSet);
        statement.close();
        return user;
    }

    @Override
    @Nullable
    @SneakyThrows
    public User findByEmail(@NotNull final String email) {
        @NotNull final String sql = "SELECT * FROM " + getTableName() + " WHERE EMAIL = ? LIMIT 1";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, email);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return null;
        @Nullable final User user = fetch(resultSet);
        statement.close();
        return user;
    }

    @Override
    @SneakyThrows
    public void removeByLogin(@NotNull final String login) {
        @NotNull final String query = "DELETE FROM " + getTableName() + " WHERE login = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, login);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void updateUser(@NotNull User user) {
        @NotNull final String query =
                "UPDATE `app_user` " +
                        "SET `first_name` = ?, `last_name` = ?, `middle_name` = ?" +
                        "WHERE `id` = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getMiddleName());
        statement.setString(4, user.getId());
        statement.execute();
    }

    @Override
    @SneakyThrows
    public void setPassword(@NotNull String password, @NotNull String userId) {
        @NotNull final String query =
                "UPDATE `app_user` " +
                        "SET `password_hash` = ? " +
                        "WHERE `id` = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, password);
        statement.setString(2, userId);
        statement.execute();
    }

    @Override
    @SneakyThrows
    public void lockUser(@NotNull User user) {
        @NotNull final String query =
                "UPDATE `app_user` " +
                        "SET `locked` = ? " +
                        "WHERE `id` = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setBoolean(1, true);
        statement.setString(2, user.getId());
        statement.execute();
    }

    @Override
    @SneakyThrows
    public void unlockUser(@NotNull User user) {
        @NotNull final String query =
                "UPDATE `app_user` " +
                        "SET `locked` = ? " +
                        "WHERE `id` = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setBoolean(1, false);
        statement.setString(2, user.getId());
        statement.execute();
    }

}
