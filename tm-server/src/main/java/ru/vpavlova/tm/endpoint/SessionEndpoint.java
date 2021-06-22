package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.ISessionEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.dto.SessionDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class SessionEndpoint extends AbstractEndpoint implements ISessionEndpoint {

    public SessionEndpoint(@NotNull ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public SessionDTO openSession(
            @WebParam(name = "login", partName = "login") @NotNull String login,
            @WebParam(name = "password", partName = "password") @NotNull String password
    ) {
        return serviceLocator.getSessionDTOService().open(login, password);
    }

    @Nullable
    @Override
    @WebMethod
    @SneakyThrows
    public SessionDTO closeSession(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        return serviceLocator.getSessionDTOService().close(session);
    }

}
