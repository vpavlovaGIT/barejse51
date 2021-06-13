package ru.vpavlova.tm.api.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.dto.SessionDTO;
import ru.vpavlova.tm.enumerated.Role;

public interface ISessionService extends IService<SessionDTO> {

    @Nullable
    SessionDTO open(String login, String password) throws Exception;

    @SneakyThrows
    void validate(@Nullable SessionDTO session,
                  @Nullable Role role) throws Exception;

    @SneakyThrows
    void validate(@Nullable SessionDTO session) throws Exception;

    @SneakyThrows
    void validateAdmin(@Nullable SessionDTO session,
                       @Nullable Role role) throws Exception;

    @Nullable SessionDTO close(@Nullable SessionDTO session) throws Exception;

    boolean checkDataAccess(@NotNull final String login,
                            @NotNull final String password) throws Exception;

    @Nullable SessionDTO sign(@Nullable final SessionDTO session);

}
