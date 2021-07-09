package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.IProjectEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.dto.Project;
import ru.vpavlova.tm.dto.Session;
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
        serviceLocator.getSessionDTOService().validate(session);
        return serviceLocator.getProjectDTOService().add(session.getUserId(), name, description);
    }

    @NotNull
    @Override
    @WebMethod
    @SneakyThrows
    public Project findProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        return serviceLocator.getProjectDTOService().findOneById(session.getUserId(), id).orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Project findProjectByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        return serviceLocator.getProjectDTOService().findOneByIndex(session.getUserId(), index).orElse(null);
    }

    @Override
    @Nullable
    @WebMethod
    @SneakyThrows
    public Project findProjectOneByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        return serviceLocator.getProjectDTOService().findOneByName(session.getUserId(), name).orElse(null);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishProjectById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().finishById(session.getUserId(), id);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishProjectByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().finishByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void finishProjectByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().finishByName(session.getUserId(), name);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeProjectById(
            @WebParam(name = "session", partName = "session") @NotNull Session session,
            @WebParam(name = "id", partName = "id") @NotNull String id
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().removeOneById(id, session.getUserId());
    }

    @NotNull
    @Override
    @WebMethod
    @SneakyThrows
    public List<Project> findAllProjects(
            @WebParam(name = "session", partName = "session") @Nullable Session session
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        return serviceLocator.getProjectDTOService().findAll();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void clear(
            @WebParam(name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().clear();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeProjectStatusById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().changeStatusById(session.getUserId(), id, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeProjectStatusByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().changeStatusByIndex(session.getUserId(), index, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void changeProjectStatusByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name,
            @WebParam(name = "status", partName = "status") @NotNull final Status status
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().changeStatusByName(session.getUserId(), name, status);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeProjectOneByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().removeOneByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeProjectOneByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().removeOneByName(session.getUserId(), name);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startProjectById(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "id", partName = "id") @NotNull final String id
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().startById(session.getUserId(), id);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startProjectByIndex(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "index", partName = "index") @NotNull final Integer index
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().startByIndex(session.getUserId(), index);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void startProjectByName(
            @WebParam(name = "session", partName = "session") @Nullable final Session session,
            @WebParam(name = "name", partName = "name") @NotNull final String name
    ) {
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().startByName(session.getUserId(), name);
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
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().updateById(session.getUserId(), id, name, description);
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
        serviceLocator.getSessionDTOService().validate(session);
        serviceLocator.getProjectDTOService().updateByIndex(session.getUserId(), index, name, description);
    }

}
