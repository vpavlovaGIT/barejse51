package ru.vpavlova.tm.service.model;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.entity.AbstractEntity;

public abstract class AbstractService<E extends AbstractEntity> implements IService<E> {

    @NotNull
    public final IConnectionService connectionService;

    public AbstractService(@NotNull IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

}
