package ru.vpavlova.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.service.*;
import ru.vpavlova.tm.api.service.dto.IProjectDTOService;
import ru.vpavlova.tm.api.service.dto.ISessionDTOService;
import ru.vpavlova.tm.api.service.dto.ITaskDTOService;
import ru.vpavlova.tm.api.service.dto.IUserDTOService;
import ru.vpavlova.tm.api.service.model.IProjectService;
import ru.vpavlova.tm.api.service.model.ISessionService;
import ru.vpavlova.tm.api.service.model.ITaskService;
import ru.vpavlova.tm.api.service.model.IUserService;
import ru.vpavlova.tm.component.Backup;
import ru.vpavlova.tm.dto.DomainDTO;
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
    private final IUserDTOService userService;

    @NotNull
    private final ITaskDTOService taskService;

    @NotNull
    private final IProjectDTOService projectService;

    @NotNull
    private final ISessionDTOService sessionService;

    public BackupService(
            @NotNull final IUserDTOService userService,
            @NotNull final ITaskDTOService taskService,
            @NotNull final IProjectDTOService projectService,
            @NotNull final ISessionDTOService sessionService
    ) {
        this.userService = userService;
        this.taskService = taskService;
        this.projectService = projectService;
        this.sessionService = sessionService;
    }


    @NotNull
    public DomainDTO getDomain() {
        @NotNull final DomainDTO domain = new DomainDTO();
        domain.setProjects(projectService.findAll());
        domain.setTasks(taskService.findAll());
        domain.setUsers(userService.findAll());
        return domain;
    }

    public void setDomain(@Nullable final DomainDTO domain) {
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
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(DomainDTO.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        @NotNull final DomainDTO domain = (DomainDTO) unmarshaller.unmarshal(file);
        setDomain(domain);
    }

    @Override
    @SneakyThrows
    public void saveBackup() {
        @NotNull final DomainDTO domain = getDomain();
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(DomainDTO.class);
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
        @NotNull final DomainDTO domain = (DomainDTO) objectInputStream.readObject();
        setDomain(domain);
        objectInputStream.close();
        byteArrayInputStream.close();
    }

    @Override
    @SneakyThrows
    public void saveDataBase64() {
        @NotNull final DomainDTO domain = getDomain();

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
        @NotNull final DomainDTO domain = (DomainDTO) objectInputStream.readObject();
        setDomain(domain);
        objectInputStream.close();
        fileInputStream.close();
    }

    @Override
    @SneakyThrows
    public void saveDataBin() {
        @NotNull final DomainDTO domain = getDomain();

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
        @NotNull final DomainDTO domain = objectMapper.readValue(json, DomainDTO.class);
        setDomain(domain);
    }

    @Override
    @SneakyThrows
    public void saveDataJson() {
        @NotNull final DomainDTO domain = getDomain();
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
        @NotNull final DomainDTO domain = objectMapper.readValue(xml, DomainDTO.class);
        setDomain(domain);
    }

    @Override
    @SneakyThrows
    public void saveDataXml() {
        @NotNull final DomainDTO domain = getDomain();
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
        @NotNull final DomainDTO domain = objectMapper.readValue(json, DomainDTO.class);
        setDomain(domain);
    }

    @Override
    @SneakyThrows
    public void saveDataYaml() {
        @NotNull final DomainDTO domain = getDomain();
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
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(DomainDTO.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setProperty(Backup.JAXB_JSON_PROPERTY_NAME, Backup.JAXB_JSON_PROPERTY_VALUE);
        @NotNull final DomainDTO domain = (DomainDTO) unmarshaller.unmarshal(file);
        setDomain(domain);
    }

    @Override
    @SneakyThrows
    public void saveDataJsonJaxB() {
        System.setProperty(Backup.SYSTEM_JSON_PROPERTY_NAME, Backup.SYSTEM_JSON_PROPERTY_VALUE);
        @NotNull final DomainDTO domain = getDomain();
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(DomainDTO.class);
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
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(DomainDTO.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        @NotNull final DomainDTO domain = (DomainDTO) unmarshaller.unmarshal(file);
        setDomain(domain);

    }

    @Override
    @SneakyThrows
    public void saveDataXmlJaxB() {
        @NotNull final DomainDTO domain = getDomain();
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(DomainDTO.class);
        @NotNull final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(Backup.FILE_JAXB_XML);
        jaxbMarshaller.marshal(domain, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

}
