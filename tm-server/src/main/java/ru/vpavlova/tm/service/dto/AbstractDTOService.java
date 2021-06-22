package ru.vpavlova.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.dto.IServiceDTO;
import ru.vpavlova.tm.dto.AbstractEntityDTO;

public abstract class AbstractDTOService<E extends AbstractEntityDTO> implements IServiceDTO<E> {

    @NotNull
    public final IConnectionService connectionService;

    public AbstractDTOService(@NotNull IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

}
