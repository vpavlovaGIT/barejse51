package ru.vpavlova.tm.api.service.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.SessionDTO;
import ru.vpavlova.tm.enumerated.Role;

public interface ISessionDTOService extends IServiceDTO<SessionDTO> {

    @Nullable SessionDTO close(@Nullable SessionDTO session);

    @Nullable
    SessionDTO open(String login, String password);

    void validate(@Nullable SessionDTO session);

    void validateAdmin(@Nullable SessionDTO session, @Nullable Role role);

    @SneakyThrows
    void remove(@Nullable SessionDTO entity);
}

