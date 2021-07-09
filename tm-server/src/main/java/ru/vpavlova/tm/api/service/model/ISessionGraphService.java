package ru.vpavlova.tm.api.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.SessionGraph;
import ru.vpavlova.tm.enumerated.Role;

public interface ISessionGraphService extends IGraphService<SessionGraph> {

    @Nullable SessionGraph close(@Nullable SessionGraph session);

    @Nullable
    SessionGraph open(String login, String password);

    void validate(@Nullable SessionGraph session);

    void validateAdmin(@Nullable SessionGraph session, @Nullable Role role);

    @SneakyThrows
    void remove(@Nullable SessionGraph entity);

}
