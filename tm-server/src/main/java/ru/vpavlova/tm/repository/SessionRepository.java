package ru.vpavlova.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.ISessionRepository;
import ru.vpavlova.tm.constant.ConstantField;
import ru.vpavlova.tm.entity.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    public SessionRepository(@NotNull Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "APP_SESSION";
    }

    @NotNull
    @Override
    @SneakyThrows
    public Session add(@Nullable final Session session) {
        if (session == null) return null;
        @NotNull final String query =
                "INSERT INTO `app_session`(`id`, `user_id`, `signature`, `timestamp`) " +
                        "VALUES(?, ?, ?, ?)";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, session.getId());
        statement.setString(2, session.getUserId());
        statement.setString(3, session.getSignature());
        statement.setLong(4, session.getTimestamp());
        statement.execute();
        return session;
    }

    @Nullable
    @SneakyThrows
    public Session fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull Session session = new Session();
        session.setId(row.getString(ConstantField.ID));
        session.setUserId(row.getString(ConstantField.USER_ID));
        session.setSignature(row.getString(ConstantField.SIGNATURE));
        session.setTimestamp(row.getLong(ConstantField.TIMESTAMP));
        return session;
    }

}
