package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.Task;
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

    @Nullable
    Task startTaskById(
            @NotNull Session session,
            @NotNull String id
    );

    @Nullable
    Task startTaskByIndex(
            @NotNull Session session,
            @NotNull Integer index
    );

    @Nullable
    Task startTaskByName(
            @NotNull Session session,
            @NotNull String name
    );

    @Nullable
    Task finishTaskById(
            @NotNull Session session,
            @NotNull String id
    );

    @Nullable
    Task finishTaskByIndex(
            @NotNull Session session,
            @NotNull Integer index
    );

    @Nullable
    Task finishTaskByName(
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

    @Nullable
    Task changeTaskStatusById(
            @NotNull Session session,
            @NotNull String id,
            @NotNull Status status
    );

    @Nullable
    Task changeTaskStatusByIndex(
            @NotNull Session session,
            @NotNull Integer index,
            @NotNull Status status
    );

    @Nullable
    Task changeTaskStatusByName(
            @NotNull Session session,
            @NotNull String name,
            @NotNull Status status
    );

    @Nullable
    @WebMethod
    Task bindTaskByProject(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "projectId", partName = "projectId") @Nullable String projectId,
            @WebParam(name = "taskId", partName = "taskId") @Nullable String taskId
    );

    @Nullable
    @WebMethod
    Task unbindTaskFromProject(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "taskId", partName = "taskId") @Nullable String taskId
    );

    void updateTaskById(
            @NotNull Session session,
            @NotNull String id,
            @NotNull String name,
            @NotNull String description
    );

    @Nullable
    Task updateTaskByIndex(
            @NotNull Session session,
            @NotNull Integer index,
            @NotNull String name,
            @NotNull String description
    );

}
