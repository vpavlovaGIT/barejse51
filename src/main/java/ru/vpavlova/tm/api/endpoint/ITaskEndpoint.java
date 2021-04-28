package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;

public interface ITaskEndpoint {

    @Nullable
    @WebMethod
    Task findTaskById(
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    );

    @WebMethod
    void removeTaskById(
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "session", partName = "session") @NotNull Session session
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

}
