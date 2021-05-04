package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.ITaskEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;

public class TaskEndpoint extends AbstractEndpoint implements ITaskEndpoint {

    public TaskEndpoint(@NotNull ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Nullable
    @Override
    @WebMethod
    @SneakyThrows
    public Task findTaskById(
            @WebParam(name = "id", partName = "id") @NotNull String id,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findById(session.getUserId(), id).orElse(null);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeTaskById(
            @WebParam (name = "id", partName = "id") @NotNull String id,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeById(id, session.getUserId());
    }

    @NotNull
    @Override
    @WebMethod
    @SneakyThrows
    public List<Task> findAllTasks(
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findAll(session.getUserId());
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void clearTasks(
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().clear(session.getUserId());
    }

}
