package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionService implements IConnectionService {

    @NotNull
    private final IPropertyService propertyService;

    public ConnectionService(@NotNull final IPropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    @SneakyThrows
    public Connection getConnection() {
        Class.forName(propertyService.getJdbcDriver());
        @NotNull final String username = propertyService.getJdbcUser();
        @NotNull final String password = propertyService.getJdbcPassword();
        @NotNull final String url = propertyService.getJdbcUrl();
        return DriverManager.getConnection(url, username, password);
    }

}
