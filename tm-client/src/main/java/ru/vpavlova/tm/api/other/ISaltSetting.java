package ru.vpavlova.tm.api.other;

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

    @NotNull
    Integer getSignIteration();

    @NotNull
    String getSignSecret();

    @NotNull
    String getServerHost();

    @NotNull
    String getServerPort();

}
