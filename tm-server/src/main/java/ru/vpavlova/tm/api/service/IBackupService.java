package ru.vpavlova.tm.api.service;

import lombok.SneakyThrows;

public interface IBackupService {

    @SneakyThrows
    void loadBackup();

    @SneakyThrows
    void saveBackup();

    @SneakyThrows
    void loadDataBase64();

    @SneakyThrows
    void saveDataBase64();

    @SneakyThrows
    void loadDataBin();

    @SneakyThrows
    void saveDataBin();

    @SneakyThrows
    void loadDataJson();

    @SneakyThrows
    void saveDataJson();

    @SneakyThrows
    void loadDataXml();

    @SneakyThrows
    void saveDataXml();

    @SneakyThrows
    void loadDataYaml();

    @SneakyThrows
    void saveDataYaml();

    @SneakyThrows
    void loadDataJsonJaxB();

    @SneakyThrows
    void saveDataJsonJaxB();

    @SneakyThrows
    void loadDataXmlJaxB();

    @SneakyThrows
    void saveDataXmlJaxB();

}
