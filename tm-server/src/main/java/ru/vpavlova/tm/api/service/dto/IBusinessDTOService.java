package ru.vpavlova.tm.api.service.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.AbstractBusinessEntityDTO;
import ru.vpavlova.tm.enumerated.Status;

import java.util.List;

public interface IBusinessDTOService<E extends AbstractBusinessEntityDTO>
        extends IServiceDTO<E> {

    void changeStatusByIndex(
            @Nullable String userId, @Nullable Integer index, @Nullable Status status
    );

    void changeStatusByName(
            @Nullable String userId, @Nullable String name, @Nullable Status status
    );

    @NotNull
    List<E> findAll(@Nullable String userId, @NotNull String sort);

    void finishById(@Nullable String userId, @Nullable String id);

    void finishByIndex(@Nullable String userId, @Nullable Integer index);

    void finishByName(@Nullable String userId, @Nullable String name);

    void startById(@Nullable String userId, @Nullable String id);

    void startByIndex(@Nullable String userId, @Nullable Integer index);

    void startByName(@Nullable String userId, @Nullable String name);

    @SneakyThrows
    void updateByIndex(
            @Nullable String userId,
            @Nullable Integer index,
            @Nullable String name,
            @Nullable String description
    );

}

