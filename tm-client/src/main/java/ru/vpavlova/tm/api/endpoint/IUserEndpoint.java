package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public interface IUserEndpoint {

    @Nullable
    @WebMethod
    User findUserByLogin(
            @WebParam (name = "login", partName = "login") @NotNull final String login,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void removeUserOneByLogin(
            @WebParam (name = "login", partName = "login") @NotNull final String login,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    );

}
