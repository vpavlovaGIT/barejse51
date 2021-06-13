package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.ITaskEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.dto.SessionDTO;
import ru.vpavlova.tm.dto.TaskDTO;
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
    public TaskDTO addTask(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
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
    public TaskDTO findTaskById(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findById(session.getUserId(), id).orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public TaskDTO findTaskOneByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findByIndex(session.getUserId(), index).orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public TaskDTO findTaskOneByName(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findByName(session.getUserId(), name).orElse(null);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeTaskById(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeById(id, session.getUserId());
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeTaskOneByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeTaskOneByName(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().removeByName(session.getUserId(), name);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startTaskById(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().startById(session.getUserId(), id);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startTaskByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().startByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startTaskByName(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().startByName(session.getUserId(), name);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishTaskById(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().finishById(session.getUserId(), id);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishTaskByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().finishByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishTaskByName(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().finishByName(session.getUserId(), name);
    }

    @NotNull
    @Override
    @WebMethod
    @SneakyThrows
    public List<TaskDTO> findAllTasks(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getTaskService().findAll();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void clearTasks(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().clear(session.getUserId());
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeTaskStatusById(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().changeStatusById(session.getUserId(), id, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeTaskStatusByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().changeStatusByIndex(session.getUserId(), index, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeTaskStatusByName(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().changeStatusByName(session.getUserId(), name, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void bindTaskByProject(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "projectId", partName = "projectId") @Nullable final String projectId,
            @WebParam(name = "taskId", partName = "taskId") @Nullable final String taskId
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectTaskService().bindTaskByProject(session.getUserId(), projectId, taskId);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void unbindTaskFromProject(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "taskId", partName = "taskId") @Nullable final String taskId) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectTaskService().unbindTaskFromProject(session.getUserId(), taskId);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void updateTaskById(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "description", partName = "description") @NotNull final String description
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().updateById(session.getUserId(), id, name, description);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void updateTaskByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final SessionDTO session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "description", partName = "description") @NotNull final String description
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService().updateByIndex(session.getUserId(), index, name, description);
    }

}
