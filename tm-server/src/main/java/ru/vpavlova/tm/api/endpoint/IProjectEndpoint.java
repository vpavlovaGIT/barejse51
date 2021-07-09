package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Project;
import ru.vpavlova.tm.dto.Session;
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

    @WebMethod
    void changeProjectStatusById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id,
            @WebParam(name = "status", partName = "status") @NotNull Status status
    );

    @WebMethod
    void finishProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    );

    @WebMethod
    void finishProjectByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index
    );

    @WebMethod
    void finishProjectByName(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "name", partName = "name") @NotNull String name
    );

    @WebMethod
    void changeProjectStatusByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index,
            @WebParam(name = "status", partName = "status") @NotNull Status status
    );

    @WebMethod
    void changeProjectStatusByName(
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

    @WebMethod
    void startProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    );

    @WebMethod
    void startProjectByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index
    );

    @WebMethod
    void startProjectByName(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "name", partName = "name") @NotNull String name
    );

    @WebMethod
    void updateProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id,
            @WebParam(name = "name", partName = "name") @NotNull String name,
            @WebParam(name = "description", partName = "description") @NotNull String description
    );

    @WebMethod
    void updateProjectByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index,
            @WebParam(name = "name", partName = "name") @NotNull String name,
            @WebParam(name = "description", partName = "description") @NotNull String description
    );

}
