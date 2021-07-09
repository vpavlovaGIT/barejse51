package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Session;
import ru.vpavlova.tm.dto.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public interface IUserEndpoint {

    @Nullable
    @WebMethod
    User findUserByLogin(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam (name = "login", partName = "login") @NotNull final String login
    );

    @Nullable
    @WebMethod
    User findUserOneBySession(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void setPassword(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "password", partName = "password") @Nullable String password
    );

}
