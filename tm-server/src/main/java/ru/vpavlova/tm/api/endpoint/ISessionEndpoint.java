package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public interface ISessionEndpoint {

    @WebMethod
    Session openSession(
            @WebParam(name = "login", partName = "login") String login,
            @WebParam(name = "password", partName = "password") String password
    );

    @Nullable
    @WebMethod
    Session closeSession(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

}
