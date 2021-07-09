package ru.vpavlova.tm.api.service.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Session;
import ru.vpavlova.tm.enumerated.Role;

public interface ISessionService extends IService<Session> {

    @Nullable Session close(@Nullable Session session);

    @Nullable
    Session open(String login, String password);

    void validate(@Nullable Session session);

    void validateAdmin(@Nullable Session session, @Nullable Role role);

    @SneakyThrows
    void remove(@Nullable Session entity);
}

