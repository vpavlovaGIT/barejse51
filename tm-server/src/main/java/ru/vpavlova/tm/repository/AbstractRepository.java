package ru.vpavlova.tm.repository;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    private final Connection connection;

    protected abstract String getTableName();

    protected abstract E fetch(@Nullable final ResultSet row);

    public AbstractRepository(@NotNull final Connection connection) {
        this.connection = connection;
    }

    @Nullable
    public java.sql.Date prepare(@Nullable final java.util.Date date) {
        if (date == null) return null;
        return new java.sql.Date(date.getTime());
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<E> findAll() {
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final String query = "SELECT * FROM " + getTableName();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        @NotNull final List<E> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Override
    public void addAll(@NotNull final List<E> entities) {
        entities.addAll(entities);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<E> findById(@NotNull final String id) {
        if (id.isEmpty()) return Optional.empty();
        @NotNull final String query = "SELECT * FROM " + getTableName() + " WHERE `id` = ? LIMIT 1";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, id);
        @NotNull ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return Optional.empty();
        @Nullable Optional<E> session = Optional.ofNullable(fetch(resultSet));
        statement.close();
        return session;
    }

    @Override
    @SneakyThrows
    public void clear() {
        @NotNull final String query = "DELETE FROM " + getTableName();
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.execute();
        statement.close();
    }

    @Override
    public void remove(@Nullable final E entity) {
        if (entity != null) {
            removeById(entity.getId());
        }
    }

    @Nullable
    @Override
    public E removeById(@NotNull final String id) {
        @NotNull final Optional<E> entity = findById(id);;
        entity.ifPresent(this::remove);
        return entity.orElse(null);
    }

    public boolean contains(@NotNull final String id) {
        return findById(id).isPresent();
    }

}
