package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;

public interface IExecutorService {

    void submit(@NotNull Runnable runnable);

}
