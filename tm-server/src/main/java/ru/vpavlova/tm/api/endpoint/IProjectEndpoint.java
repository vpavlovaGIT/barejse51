package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.enumerated.Status;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;

public interface IProjectEndpoint {

    @NotNull
    @WebMethod
    Project addProject(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "name", partName = "name") @NotNull String name,
            @WebParam(name = "description", partName = "description") @NotNull String description
    );

    @Nullable
    @WebMethod
    Project findProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    );

    @WebMethod
    void removeProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    );

    @NotNull
    @WebMethod
    List<Project> findAllProjects(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    void clear(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @Nullable
    @WebMethod
    Project findProjectByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index
    );

    @Nullable
    @WebMethod
    Project findProjectOneByName(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "name", partName = "name") @NotNull String name
    );

    @Nullable
    @WebMethod
    Project changeProjectStatusById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id,
            @WebParam(name = "status", partName = "status") @NotNull Status status
    );

    @Nullable
    @WebMethod
    Project finishProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    );

    @Nullable
    @WebMethod
    Project finishProjectByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index
    );

    @Nullable
    @WebMethod
    Project finishProjectByName(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "name", partName = "name") @NotNull String name
    );

    @Nullable
    @WebMethod
    Project changeProjectStatusByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index,
            @WebParam(name = "status", partName = "status") @NotNull Status status
    );

    @Nullable
    @WebMethod
    Project changeProjectStatusByName(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "name", partName = "name") @NotNull String name,
            @WebParam(name = "status", partName = "status") @NotNull Status status
    );

    @WebMethod
    void removeProjectOneByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index
    );

    @WebMethod
    void removeProjectOneByName(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "name", partName = "name") @NotNull String name
    );

    @Nullable
    @WebMethod
    Project startProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    );

    @Nullable
    @WebMethod
    Project startProjectByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index
    );

    @Nullable
    @WebMethod
    Project startProjectByName(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "name", partName = "name") @NotNull String name
    );

    @Nullable
    @WebMethod
    Project updateProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id,
            @WebParam(name = "name", partName = "name") @NotNull String name,
            @WebParam(name = "description", partName = "description") @NotNull String description
    );

    @Nullable
    @WebMethod
    Project updateProjectByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index,
            @WebParam(name = "name", partName = "name") @NotNull String name,
            @WebParam(name = "description", partName = "description") @NotNull String description
    );

}
