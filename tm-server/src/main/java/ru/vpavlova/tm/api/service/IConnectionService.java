package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;

public interface IConnectionService {

    @NotNull
    Connection getConnection();

}
