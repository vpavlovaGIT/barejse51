package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;

public interface IProjectEndpoint {

    @Nullable
    @WebMethod
    Project findProjectById(
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void removeProjectById(
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @NotNull
    @WebMethod
    List<Project> findAllProjects(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    void clear(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

}
