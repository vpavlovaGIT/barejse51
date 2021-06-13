package ru.vpavlova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.dto.SessionDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public interface IAdminDataEndpoint {

    @WebMethod
    void loadBackup(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void saveBackup(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void loadDataBase64(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void saveDataBase64(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void loadDataBin(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void saveDataBin(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void loadDataJson(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void saveDataJson(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void loadDataXml(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void saveDataXml(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void loadDataYaml(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void saveDataYaml(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void loadDataJsonJaxB(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void saveDataJsonJaxB(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void loadDataXmlJaxB(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

    @WebMethod
    void saveDataXmlJaxB(
            @WebParam(name = "session", partName = "session") @NotNull SessionDTO session
    );

}
