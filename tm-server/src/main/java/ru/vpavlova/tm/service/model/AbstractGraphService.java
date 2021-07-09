package ru.vpavlova.tm.service.model;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.model.IGraphService;
import ru.vpavlova.tm.entity.AbstractGraphEntity;

public abstract class AbstractGraphService<E extends AbstractGraphEntity> implements IGraphService<E> {


    @NotNull
    public final IConnectionService connectionService;

    public AbstractGraphService(@NotNull IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

}
