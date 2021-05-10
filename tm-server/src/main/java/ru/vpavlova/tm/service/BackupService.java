package ru.vpavlova.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.service.IBackupService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.dto.Domain;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class BackupService implements IBackupService {

    @NotNull
    protected static final String FILE_BACKUP = "./backup.xml";

    @Nullable
    protected ServiceLocator serviceLocator;

    public BackupService(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @NotNull
    @SneakyThrows
    private Domain getDomain() {
        @NotNull final Domain domain = new Domain();
        if (serviceLocator == null) throw new ObjectNotFoundException();
        domain.setProjects(serviceLocator.getProjectService().findAll());
        domain.setTasks(serviceLocator.getTaskService().findAll());
        domain.setUsers(serviceLocator.getUserService().findAll());
        return domain;
    }

    @SneakyThrows
    private void setDomain(@Nullable final Domain domain) {
        if (domain == null) return;
        if (serviceLocator == null) throw new ObjectNotFoundException();
        serviceLocator.getProjectService().clear();
        serviceLocator.getProjectService().addAll(domain.getProjects());
        serviceLocator.getTaskService().clear();
        serviceLocator.getTaskService().addAll(domain.getTasks());
        serviceLocator.getUserService().clear();
        serviceLocator.getUserService().addAll(domain.getUsers());
    }

    @Override
    @SneakyThrows
    public void load() {
        @NotNull final File file = new File(FILE_BACKUP);
        if (!file.exists()) return;
        @NotNull final String xml = new String(Files.readAllBytes(Paths.get(FILE_BACKUP)));
        @NotNull final ObjectMapper objectMapper = new XmlMapper();
        @NotNull final Domain domain = objectMapper.readValue(xml, Domain.class);
        setDomain(domain);
    }

    @Override
    @SneakyThrows
    public void save() {
        @NotNull final Domain domain = getDomain();
        @NotNull final ObjectMapper objectMapper = new XmlMapper();
        @NotNull final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        @NotNull final String xml = objectWriter.writeValueAsString(domain);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(FILE_BACKUP);
        fileOutputStream.write(xml.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

}
