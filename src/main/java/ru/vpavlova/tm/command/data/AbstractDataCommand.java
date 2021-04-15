package ru.vpavlova.tm.command.data;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

public abstract class AbstractDataCommand extends AbstractCommand {

    @NotNull
    protected static final String FILE_BINARY = "./data.bin";

    @NotNull
    protected static final String FILE_BASE64 = "./data.base64";

    @NotNull
    protected static final String FILE_JSON_FASTERXML = "./data-fasterxml.json";

    @NotNull
    protected static final String FILE_JSON_JAXB = "./data-jaxb.json";

    @NotNull
    protected static final String FILE_XML_FASTERXML = "./data-fasterxml.xml";

    @NotNull
    protected static final String FILE_XML_JAXB = "./data-jaxb.xml";

    @NotNull
    protected static final String FILE_YAML_FASTERXML = "./data-fasterxml.yaml";

    @NotNull
    protected static final String JAVAX_XML_BIND_CONTEXT_FACTORY = "javax.xml.bind.context.factory";

    @NotNull
    protected static final String ORG_ECLIPSE_PERSISTENCE_JAXB_JAXBCONTEXT_FACTORY = "org.eclipse.persistence.jaxb.JAXBContextFactory";

    @NotNull
    protected static final String ECLIPSELINK_MEDIA_TYPE = "eclipselink.media-type";

    @NotNull
    protected static final String APPLICATION_JSON = "application/json";

    @NotNull
    protected static final String BACKUP_XML = "./backup.xml";

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
