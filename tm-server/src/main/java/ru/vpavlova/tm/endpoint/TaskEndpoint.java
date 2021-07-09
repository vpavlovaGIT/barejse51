package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.ITaskEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.dto.Session;
import ru.vpavlova.tm.dto.Task;
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
        serviceLocator.getSessionDTOService().validate(session);
        return serviceLocator.getTaskDTOService().add(session.getUserId(), name, description);
    }

    @Nullable
    @Override
    @WebMethod
    @SneakyThrows
    public Task findTaskById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        return serviceLocator.getTaskDTOService().findOneById(session.getUserId(), id).orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task findTaskOneByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        return serviceLocator.getTaskDTOService().findOneByIndex(session.getUserId(), index).orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Task findTaskOneByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        return serviceLocator.getTaskDTOService().findOneByName(session.getUserId(), name).orElse(null);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeTaskById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().removeOneById(id, session.getUserId());
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeTaskOneByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().removeOneByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeTaskOneByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().removeOneByName(session.getUserId(), name);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startTaskById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().startById(session.getUserId(), id);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startTaskByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().startByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startTaskByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().startByName(session.getUserId(), name);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishTaskById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().finishById(session.getUserId(), id);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishTaskByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().finishByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishTaskByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().finishByName(session.getUserId(), name);
    }

    @NotNull
    @Override
    @WebMethod
    @SneakyThrows
    public List<Task> findAllTasks(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        return serviceLocator.getTaskDTOService().findAll();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void clearTasks(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().clear(session.getUserId());
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeTaskStatusById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().changeStatusById(session.getUserId(), id, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeTaskStatusByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().changeStatusByIndex(session.getUserId(), index, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeTaskStatusByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().changeStatusByName(session.getUserId(), name, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void bindTaskByProject(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "projectId", partName = "projectId") @Nullable final String projectId,
            @WebParam(name = "taskId", partName = "taskId") @Nullable final String taskId
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectTaskDTOService().bindTaskByProject(session.getUserId(), projectId, taskId);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void unbindTaskFromProject(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "taskId", partName = "taskId") @Nullable final String taskId) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectTaskDTOService().unbindTaskFromProject(session.getUserId(), taskId);
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
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().updateById(session.getUserId(), id, name, description);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void updateTaskByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "description", partName = "description") @NotNull final String description
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getTaskDTOService().updateByIndex(session.getUserId(), index, name, description);
    }

}
