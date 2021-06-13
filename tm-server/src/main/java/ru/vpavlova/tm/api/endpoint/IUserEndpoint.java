package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.SessionDTO;
import ru.vpavlova.tm.dto.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public interface IUserEndpoint {

    @Nullable
    @WebMethod
    UserDTO findUserByLogin(
            @WebParam (name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam (name = "login", partName = "login") @NotNull final String login
    );

    @Nullable
    @WebMethod
    UserDTO findUserOneBySession(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void setPassword(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "password", partName = "password") @Nullable String password
    );

}
