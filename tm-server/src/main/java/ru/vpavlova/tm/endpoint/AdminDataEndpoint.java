package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.endpoint.IAdminDataEndpoint;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.component.Backup;
import ru.vpavlova.tm.dto.SessionDTO;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.service.BackupService;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public class AdminDataEndpoint extends AbstractEndpoint implements IAdminDataEndpoint {

    private Backup backup;

    public AdminDataEndpoint(Bootstrap bootstrap, @NotNull BackupService backupService) {
        super(null);
    }

    public AdminDataEndpoint(
            @NotNull final ServiceLocator serviceLocator,
            @NotNull final Backup backup
    ) {
        super(serviceLocator);
        this.backup = backup;
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void loadBackup(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        backup.load();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void saveBackup(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        backup.run();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void loadDataBase64(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().loadDataBase64();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void saveDataBase64(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().saveDataBase64();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void loadDataBin(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().loadDataBin();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void saveDataBin(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().saveDataBin();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void loadDataJson(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().loadDataJson();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void saveDataJson(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().saveDataJson();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void loadDataXml(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().loadDataXml();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void saveDataXml(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().saveDataXml();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void loadDataYaml(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().loadDataYaml();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void saveDataYaml(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().saveDataYaml();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void loadDataJsonJaxB(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().loadDataJsonJaxB();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void saveDataJsonJaxB(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().saveDataJsonJaxB();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void loadDataXmlJaxB(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().loadDataXmlJaxB();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public void saveDataXmlJaxB(
            @WebParam(name = "session", partName = "session") @NotNull final SessionDTO session
    ) {
        serviceLocator.getSessionService().validateAdmin(session, Role.ADMIN);
        serviceLocator.getDataService().saveDataXmlJaxB();
    }

}
