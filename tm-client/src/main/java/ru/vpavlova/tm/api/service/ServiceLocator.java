package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;

public interface ServiceLocator {

    @NotNull
    ICommandService getCommandService();

    @NotNull
    IPropertyService getPropertyService();

}
