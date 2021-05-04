package ru.vpavlova.tm.api.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.enumerated.Role;

public interface ISessionService extends IService<Session> {

    @Nullable
    Session open(String login, String password) throws Exception;

    @SneakyThrows
    void validate(@Nullable Session session,
                  @Nullable Role role) throws Exception;

    @SneakyThrows
    void validate(@Nullable Session session) throws Exception;

    @SneakyThrows
    void validateAdmin(@Nullable Session session,
                       @Nullable Role role) throws Exception;

    @Nullable Session close(@Nullable Session session) throws Exception;

    boolean checkDataAccess(@NotNull final String login,
                            @NotNull final String password) throws Exception;

    @Nullable Session sign(@Nullable final Session session);

}
