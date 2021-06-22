package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;

public interface IConnectionService {

    @NotNull
    EntityManager getEntityManager();

}
