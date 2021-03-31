package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.Nullable;

public interface ILoggerService {

    void info(@Nullable String message);

    void debug(@Nullable String message);

    void command(@Nullable String message);

    void error(@Nullable Exception e);

}
