package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.IProjectEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.enumerated.Status;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class ProjectEndpoint extends AbstractEndpoint implements IProjectEndpoint {

    public ProjectEndpoint(@NotNull ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @NotNull
    @WebMethod
    @SneakyThrows
    public Project addProject(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "description", partName = "description") @NotNull final String description
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().add(session.getUserId(), name, description);
    }

    @NotNull
    @Override
    @WebMethod
    @SneakyThrows
    public Project findProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findOneById(session.getUserId(), id).orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Project findProjectByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findByIndex(session.getUserId(), index).orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Project findProjectOneByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findByName(session.getUserId(), name).orElse(null);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishProjectById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().finishById(session.getUserId(), id);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishProjectByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().finishByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishProjectByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().finishByName(session.getUserId(), name);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().removeById(id, session.getUserId());
    }

    @NotNull
    @Override
    @WebMethod
    @SneakyThrows
    public List<Project> findAllProjects(
            @WebParam(name = "session", partName = "session") @Nullable Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findAll();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void clear(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().clear();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeProjectStatusById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().changeStatusById(session.getUserId(), id, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeProjectStatusByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().changeStatusByIndex(session.getUserId(), index, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeProjectStatusByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().changeStatusByName(session.getUserId(), name, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeProjectOneByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().removeByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeProjectOneByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().removeByName(session.getUserId(), name);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startProjectById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().startById(session.getUserId(), id);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startProjectByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().startByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startProjectByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().startByName(session.getUserId(), name);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void updateProjectById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "description", partName = "description") @NotNull final String description
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().updateById(session.getUserId(), id, name, description);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void updateProjectByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "description", partName = "description") @NotNull final String description
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().updateByIndex(session.getUserId(), index, name, description);
    }

}
