package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.endpoint.IProjectEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class ProjectEndpoint extends AbstractEndpoint implements IProjectEndpoint {

    public ProjectEndpoint(@NotNull ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Nullable
    @Override
    @WebMethod
    @SneakyThrows
    public Project findProjectById(
            @WebParam(name = "id", partName = "id") @NotNull String id,
            @WebParam(name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findById(session.getUserId(), id).orElse(null);
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void removeProjectById(
            @WebParam (name = "id", partName = "id") @NotNull String id,
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().removeById(id, session.getUserId());
    }

    @NotNull
    @Override
    @WebMethod
    @SneakyThrows
    public List<Project> findAllProjects(
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getProjectService().findAll(session.getUserId());
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void clear(
            @WebParam (name = "session", partName = "session") @NotNull Session session
    ) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getProjectService().clear(session.getUserId());
    }

}
