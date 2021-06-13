package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.SessionDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public interface ISessionEndpoint {

    @WebMethod
    SessionDTO openSession(
            @WebParam(name = "login", partName = "login") String login,
            @WebParam(name = "password", partName = "password") String password
    );

    @Nullable
    @WebMethod
    SessionDTO closeSession(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

}
