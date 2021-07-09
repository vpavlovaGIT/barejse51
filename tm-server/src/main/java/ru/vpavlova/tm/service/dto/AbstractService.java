package ru.vpavlova.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.dto.IService;
import ru.vpavlova.tm.dto.AbstractEntity;

public abstract class AbstractService<E extends AbstractEntity> implements IService<E> {

    @NotNull
    public final IConnectionService connectionService;

    public AbstractService(@NotNull IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

}
