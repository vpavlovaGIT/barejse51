package ru.vpavlova.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public abstract class AbstractBusinessRepository<E extends AbstractBusinessEntity> extends AbstractRepository<E> implements IBusinessRepository<E> {

    public AbstractBusinessRepository(Connection connection) {
        super(connection);
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<E> findAll(@NotNull final String userId) {
        @NotNull final String query = "SELECT * FROM " + getTableName() + " WHERE `user_id` = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        @NotNull ResultSet resultSet = statement.executeQuery();
        @NotNull List<E> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Nullable
    @Override
    public E add(@NotNull final String userId, @NotNull final E entity) {
        if (entity == null) return null;
        entity.setUserId(userId);
        return add(entity);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<E> findById(@NotNull final String userId, @NotNull final String id) {
        if (id.isEmpty()) return Optional.empty();
        @NotNull final String query = "SELECT * FROM " + getTableName() +
                " WHERE `id` = ? " +
                "AND `user_id` = ? LIMIT 1";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, userId);
        @NotNull ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return Optional.empty();
        @Nullable Optional<E> entity = Optional.ofNullable(fetch(resultSet));
        statement.close();
        return entity;
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<E> findByIndex(@NotNull final String userId, @NotNull final Integer index) {
        if (userId.isEmpty()) return Optional.empty();
        @NotNull final String query =
                "SELECT * FROM " + getTableName() +
                        " WHERE `user_id` = ?" +
                        " LIMIT ?, 1";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        statement.setInt(2, index);
        @NotNull ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return Optional.empty();
        @Nullable Optional<E> entity = Optional.ofNullable(fetch(resultSet));
        statement.close();
        return entity;
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<E> findByName(@NotNull final String userId, @NotNull final String name) {
        if (userId.isEmpty()) return Optional.empty();
        @NotNull final String query =
                "SELECT * FROM " + getTableName() +
                        " WHERE `name` = ?" +
                        " AND `user_id` = ? LIMIT 1";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, userId);
        @NotNull ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return Optional.empty();
        @Nullable Optional<E> entity = Optional.ofNullable(fetch(resultSet));
        statement.close();
        return entity;
    }

    @Override
    @SneakyThrows
    public void clear(@NotNull final String userId) {
        if (userId.isEmpty()) return;
        @NotNull final String query = "DELETE FROM " + getTableName() + " WHERE `user_id` = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        statement.execute();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void removeById(@NotNull final String userId, @NotNull final String id) {
        @NotNull final String query =
                "DELETE FROM " + getTableName() +
                        " WHERE `id` = ?" +
                        " AND `user_id` = ? LIMIT 1";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, userId);
        statement.execute();
        statement.close();
    }

    @Override
    public void removeByIndex(@NotNull final String userId, @NotNull final Integer index) {
        final @NotNull Optional<E> entity = findByIndex(userId, index);
        if (entity == null) throw new ObjectNotFoundException();
        removeById(entity.get().getId());
    }

    @Override
    @SneakyThrows
    public void removeByName(@NotNull final String userId, @NotNull final String name) {
        @NotNull final String query =
                "DELETE FROM " + getTableName() +
                        " WHERE `name` = ?" +
                        " AND `user_id` = ? LIMIT 1";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, userId);
        statement.execute();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void remove(@NotNull final String userId, @NotNull final E entity) {
        @NotNull final String query = "DELETE FROM " + getTableName() + " WHERE id = ? AND user_id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, entity.getId());
        statement.setString(2, userId);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public Project updateById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final String name,
            @Nullable final String description
    ) {
        if (id.isEmpty()) return null;
        if (name.isEmpty()) return null;
        if (description.isEmpty()) return null;
        @NotNull final String query =
                "UPDATE " + getTableName() +
                        " SET `name` = ?," +
                        " `description` = ?" +
                        " WHERE `id` = ?" +
                        " LIMIT 1";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, description);
        statement.setString(3, id);
        statement.execute();
        statement.close();
        return null;
    }

}
