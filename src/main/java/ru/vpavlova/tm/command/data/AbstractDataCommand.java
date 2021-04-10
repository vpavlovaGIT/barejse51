package ru.vpavlova.tm.command.data;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

public abstract class AbstractDataCommand extends AbstractCommand {

    protected static final String FILE_BINARY = "./data.bin";

    protected static final String FILE_BASE64 = "./data.base64";

    @NotNull
    @SneakyThrows
    public Domain getDomain() {
        @NotNull final Domain domain = new Domain();
        if (serviceLocator == null) throw new ObjectNotFoundException();
        domain.setProjects(serviceLocator.getProjectService().findAll());
        domain.setTasks(serviceLocator.getTaskService().findAll());
        domain.setUsers(serviceLocator.getUserService().findAll());
        return domain;
    }

    @SneakyThrows
    public void setDomain(@Nullable final Domain domain) {
        if (domain == null) return;
        if (serviceLocator == null) throw new ObjectNotFoundException();
        serviceLocator.getProjectService().clear();
        serviceLocator.getProjectService().addAll(domain.getProjects());
        serviceLocator.getTaskService().clear();
        serviceLocator.getTaskService().addAll(domain.getTasks());
        serviceLocator.getUserService().clear();
        serviceLocator.getUserService().addAll(domain.getUsers());
        serviceLocator.getAuthService().logout();
    }

}
