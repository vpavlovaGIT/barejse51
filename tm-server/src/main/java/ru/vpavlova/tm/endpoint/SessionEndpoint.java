package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.ISessionEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.entity.Session;

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
    public Session openSession(
            @WebParam(name = "login", partName = "login") String login,
            @WebParam(name = "password", partName = "password") String password
    ) {
        return serviceLocator.getSessionService().open(login, password);
    }

    @Nullable
    @Override
    @WebMethod
    @SneakyThrows
    public Session closeSession(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getSessionService().close(session);
    }

}
