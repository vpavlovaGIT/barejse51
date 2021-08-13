package ru.vpavlova.tm.api.service;

import lombok.SneakyThrows;

public interface IActiveMQConnectionService {

    @SneakyThrows
    void shutDown();

}
