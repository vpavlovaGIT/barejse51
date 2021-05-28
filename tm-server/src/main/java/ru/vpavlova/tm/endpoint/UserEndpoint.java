package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.IUserEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class UserEndpoint extends AbstractEndpoint implements IUserEndpoint {

    public UserEndpoint(@NotNull ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Nullable
    @Override
    @WebMethod
    @SneakyThrows
    public User findUserByLogin(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "login", partName = "login") @NotNull final String login
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        return serviceLocator.getUserService().findByLogin(login);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public User findUserOneBySession(
            @WebParam(name = "session", partName = "session") @Nullable final Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getUserService().findById(session.getUserId()).orElse(null);
    }

}
