package ru.vpavlova.tm.api.service;

import lombok.SneakyThrows;

public interface IBackupService {

    @SneakyThrows
    void load();

    @SneakyThrows
    void save();

}
