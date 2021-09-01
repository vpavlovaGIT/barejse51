package ru.vpavlova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.service.ServiceLocator;

public class AbstractEndpoint {

    @NotNull
    final ServiceLocator serviceLocator;

    public AbstractEndpoint( ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

}
