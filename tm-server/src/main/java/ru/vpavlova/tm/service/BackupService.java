package ru.vpavlova.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.service.IBackupService;
import ru.vpavlova.tm.api.service.dto.IProjectService;
import ru.vpavlova.tm.api.service.dto.ISessionService;
import ru.vpavlova.tm.api.service.dto.ITaskService;
import ru.vpavlova.tm.api.service.dto.IUserService;
import ru.vpavlova.tm.component.Backup;
import ru.vpavlova.tm.dto.Domain;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class BackupService implements IBackupService {

    @NotNull
    private final IUserService userService;

    @NotNull
    private final ITaskService taskService;

    @NotNull
    private final IProjectService projectService;

    @NotNull
    private final ISessionService sessionService;

    public BackupService(
            @NotNull final IUserService userService,
            @NotNull final ITaskService taskService,
            @NotNull final IProjectService projectService,
            @NotNull final ISessionService sessionService
    ) {
        this.userService = userService;
        this.taskService = taskService;
        this.projectService = projectService;
        this.sessionService = sessionService;
    }


    @NotNull
    public Domain getDomain() {
        @NotNull final Domain domain = new Domain();
        domain.setProjects(projectService.findAll());
        domain.setTasks(taskService.findAll());
        domain.setUsers(userService.findAll());
        return domain;
    }

    public void setDomain(@Nullable final Domain domain) {
        if (domain == null) return;
        projectService.clear();
        projectService.addAll(domain.getProjects());
        taskService.clear();
        taskService.addAll(domain.getTasks());
        userService.clear();
        userService.addAll(domain.getUsers());
    }

    @Override
    @SneakyThrows
    public void loadBackup() {
        @NotNull final File file = new File(Backup.BACKUP_XML);
        if (!file.exists()) return;
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(file);
        setDomain(domain);
    }

    @Override
    @SneakyThrows
    public void saveBackup() {
        @NotNull final Domain domain = getDomain();
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(Backup.BACKUP_XML);
        jaxbMarshaller.marshal(domain, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @Override
    @SneakyThrows
    public void loadDataBase64() {
        @NotNull final String base64data = new String(Files.readAllBytes(Paths.get(Backup.FILE_BASE64)));
        final byte[] decodeData = new BASE64Decoder().decodeBuffer(base64data);
        @NotNull final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodeData);
        @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        @NotNull final Domain domain = (Domain) objectInputStream.readObject();
        setDomain(domain);
        objectInputStream.close();
        byteArrayInputStream.close();
    }

    @Override
    @SneakyThrows
    public void saveDataBase64() {
        @NotNull final Domain domain = getDomain();

        @NotNull final File file = new File(Backup.FILE_BASE64);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());

        @NotNull final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        @NotNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(domain);
        objectOutputStream.close();
        byteArrayOutputStream.close();

        final byte[] bytes = byteArrayOutputStream.toByteArray();
        @NotNull final String base64 = new BASE64Encoder().encode(bytes);

        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(Backup.FILE_BASE64);
        fileOutputStream.write(base64.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @Override
    @SneakyThrows
    public void loadDataBin() {
        @NotNull final FileInputStream fileInputStream = new FileInputStream(Backup.FILE_BINARY);
        @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        @NotNull final Domain domain = (Domain) objectInputStream.readObject();
        setDomain(domain);
        objectInputStream.close();
        fileInputStream.close();
    }

    @Override
    @SneakyThrows
    public void saveDataBin() {
        @NotNull final Domain domain = getDomain();

        @NotNull final File file = new File(Backup.FILE_BINARY);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());

        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        @NotNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(domain);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    @Override
    @SneakyThrows
    public void loadDataJson() {
        @NotNull final String json = new String(Files.readAllBytes(Paths.get(Backup.FILE_FASTERXML_JSON)));
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final Domain domain = objectMapper.readValue(json, Domain.class);
        setDomain(domain);
    }

    @Override
    @SneakyThrows
    public void saveDataJson() {
        @NotNull final Domain domain = getDomain();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final String xml = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(Backup.FILE_FASTERXML_JSON);
        fileOutputStream.write(xml.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @Override
    @SneakyThrows
    public void loadDataXml() {
        @NotNull final String xml = new String(Files.readAllBytes(Paths.get(Backup.FILE_FASTERXML_XML)));
        @NotNull final ObjectMapper objectMapper = new XmlMapper();
        @NotNull final Domain domain = objectMapper.readValue(xml, Domain.class);
        setDomain(domain);
    }

    @Override
    @SneakyThrows
    public void saveDataXml() {
        @NotNull final Domain domain = getDomain();
        @NotNull final ObjectMapper objectMapper = new XmlMapper();
        @NotNull final String xml = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(Backup.FILE_FASTERXML_XML);
        fileOutputStream.write(xml.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @Override
    @SneakyThrows
    public void loadDataYaml() {
        @NotNull final String json = new String(Files.readAllBytes(Paths.get(Backup.FILE_FASTERXML_YAML)));
        @NotNull final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        @NotNull final Domain domain = objectMapper.readValue(json, Domain.class);
        setDomain(domain);
    }

    @Override
    @SneakyThrows
    public void saveDataYaml() {
        @NotNull final Domain domain = getDomain();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        @NotNull final String xml = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(Backup.FILE_FASTERXML_YAML);
        fileOutputStream.write(xml.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @Override
    @SneakyThrows
    public void loadDataJsonJaxB() {
        System.setProperty(Backup.SYSTEM_JSON_PROPERTY_NAME, Backup.SYSTEM_JSON_PROPERTY_VALUE);
        @NotNull final File file = new File(Backup.FILE_JAXB_JSON);
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setProperty(Backup.JAXB_JSON_PROPERTY_NAME, Backup.JAXB_JSON_PROPERTY_VALUE);
        @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(file);
        setDomain(domain);
    }

    @Override
    @SneakyThrows
    public void saveDataJsonJaxB() {
        System.setProperty(Backup.SYSTEM_JSON_PROPERTY_NAME, Backup.SYSTEM_JSON_PROPERTY_VALUE);
        @NotNull final Domain domain = getDomain();
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Backup.JAXB_JSON_PROPERTY_NAME, Backup.JAXB_JSON_PROPERTY_VALUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(Backup.FILE_JAXB_JSON);
        jaxbMarshaller.marshal(domain, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();

    }

    @Override
    @SneakyThrows
    public void loadDataXmlJaxB() {
        @NotNull final File file = new File(Backup.FILE_JAXB_XML);
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(file);
        setDomain(domain);

    }

    @Override
    @SneakyThrows
    public void saveDataXmlJaxB() {
        @NotNull final Domain domain = getDomain();
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(Backup.FILE_JAXB_XML);
        jaxbMarshaller.marshal(domain, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

}
