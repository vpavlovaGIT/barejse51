package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Session;
import ru.vpavlova.tm.dto.Task;
import ru.vpavlova.tm.enumerated.Status;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;

public interface ITaskEndpoint {

    @NotNull
    @WebMethod
    Task addTask(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "name", partName = "name") @NotNull String name,
            @WebParam(name = "description", partName = "description") @NotNull String description
    );

    @Nullable
    @WebMethod
    Task findTaskById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    );

    @Nullable
    @WebMethod
    Task findTaskOneByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index
    );

    @Nullable
    @WebMethod
    Task findTaskOneByName(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "name", partName = "name") @NotNull String name
    );

    @WebMethod
    void removeTaskById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    );

    @WebMethod
    void removeTaskOneByIndex(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "index", partName = "index") @NotNull Integer index
    );

    @WebMethod
    void removeTaskOneByName(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "name", partName = "name") @NotNull String name
    );

    @WebMethod
    void startTaskById(
            @NotNull Session session,
            @NotNull String id
    );

    @WebMethod
    void startTaskByIndex(
            @NotNull Session session,
            @NotNull Integer index
    );

    @WebMethod
    void startTaskByName(
            @NotNull Session session,
            @NotNull String name
    );

    @WebMethod
    void finishTaskById(
            @NotNull Session session,
            @NotNull String id
    );

    @WebMethod
    void finishTaskByIndex(
            @NotNull Session session,
            @NotNull Integer index
    );

    @WebMethod
    void finishTaskByName(
            @NotNull Session session,
            @NotNull String name
    );

    @NotNull
    @WebMethod
    List<Task> findAllTasks(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void clearTasks(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void changeTaskStatusById(
            @NotNull Session session,
            @NotNull String id,
            @NotNull Status status
    );

    @WebMethod
    void changeTaskStatusByIndex(
            @NotNull Session session,
            @NotNull Integer index,
            @NotNull Status status
    );

    @WebMethod
    void changeTaskStatusByName(
            @NotNull Session session,
            @NotNull String name,
            @NotNull Status status
    );

    @WebMethod
    void bindTaskByProject(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "projectId", partName = "projectId") @Nullable String projectId,
            @WebParam(name = "taskId", partName = "taskId") @Nullable String taskId
    );

    @WebMethod
    void unbindTaskFromProject(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "taskId", partName = "taskId") @Nullable String taskId
    );

    @WebMethod
    void updateTaskById(
            @NotNull Session session,
            @NotNull String id,
            @NotNull String name,
            @NotNull String description
    );

    @WebMethod
    void updateTaskByIndex(
            @NotNull Session session,
            @NotNull Integer index,
            @NotNull String name,
            @NotNull String description
    );

}
