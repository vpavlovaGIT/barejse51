package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.ITaskEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.enumerated.Status;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class TaskEndpoint extends AbstractEndpoint implements ITaskEndpoint {

    public TaskEndpoint(@NotNull ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @NotNull
    @WebMethod
    @SneakyThrows
    public Task addTask(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "description", partName = "description") @NotNull final String description
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().add(session.getUserId(), name, description);
    }

    @Nullable
    @Override
    @WebMethod
    @SneakyThrows
    public Task findTaskById(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findById(session.getUserId(), id).orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task findTaskOneByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findByIndex(session.getUserId(), index).orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task findTaskOneByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findByName(session.getUserId(), name).orElse(null);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeTaskById(
            @WebParam (name = "session", partName = "session") @NotNull Session session,
            @WebParam (name = "id", partName = "id") @NotNull String id
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeById(id, session.getUserId());
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeTaskOneByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeTaskOneByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeByName(session.getUserId(), name);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task startTaskById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().startById(session.getUserId(), id)
                .orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task startTaskByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().startByIndex(session.getUserId(), index)
                .orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task startTaskByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().startByName(session.getUserId(), name)
                .orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task finishTaskById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().finishById(session.getUserId(), id)
                .orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task finishTaskByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().finishByIndex(session.getUserId(), index)
                .orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task finishTaskByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().finishByName(session.getUserId(), name)
                .orElse(null);
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

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task changeTaskStatusById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().changeStatusById(session.getUserId(), id, status)
                .orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task changeTaskStatusByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().changeStatusByIndex(session.getUserId(), index, status)
                .orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task changeTaskStatusByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().changeStatusByName(session.getUserId(), name, status)
                .orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task bindTaskByProject(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "projectId", partName = "projectId") @Nullable final String projectId,
            @WebParam(name = "taskId", partName = "taskId") @Nullable final String taskId
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectTaskService().bindTaskByProject(session.getUserId(), projectId, taskId)
                .orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task unbindTaskFromProject(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "taskId", partName = "taskId") @Nullable final String taskId) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectTaskService().unbindTaskFromProject(session.getUserId(), taskId)
                .orElse(null);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void updateTaskById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "description", partName = "description") @NotNull final String description
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().updateById(session.getUserId(), id, name, description);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task updateTaskByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "description", partName = "description") @NotNull final String description
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().updateByIndex(session.getUserId(), index, name, description)
                .orElse(null);
    }

}
