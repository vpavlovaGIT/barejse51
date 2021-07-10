package ru.vpavlova.tm.api;

import org.jetbrains.annotations.NotNull;

public interface ISaltSetting {

    @NotNull
    Integer getPasswordIteration();

    @NotNull
    String getPasswordSecret();

    @NotNull
    Integer getSignIteration();

    @NotNull
    String getSignSecret();

}
