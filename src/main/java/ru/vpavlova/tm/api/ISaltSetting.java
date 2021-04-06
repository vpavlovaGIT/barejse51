package ru.vpavlova.tm.api;

import org.jetbrains.annotations.NotNull;

public interface ISaltSetting {

    @NotNull
    String getPasswordSecret();

    @NotNull
    Integer getPasswordIteration();

    @NotNull
    String getApplicationVersion();

    @NotNull
    String getDeveloperName();

    @NotNull
    String getDeveloperEmail();

    @NotNull
    String getDeveloperCompany();

}
