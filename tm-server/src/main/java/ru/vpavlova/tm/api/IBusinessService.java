package ru.vpavlova.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.AbstractBusinessEntityDTO;
import ru.vpavlova.tm.enumerated.Status;

public interface IBusinessService<E extends AbstractBusinessEntityDTO>
        extends IService<E> {

    void updateByIndex(
            @Nullable String userId,
            @Nullable Integer index,
            @Nullable String name,
            @Nullable String description
    );

    void updateById(
            @Nullable String userId,
            @Nullable String id,
            @Nullable String name,
            @Nullable String description
    );

    void startById(@Nullable String userId, @Nullable String id);

    void startByIndex(@Nullable String userId, @Nullable Integer index);

    void startByName(@Nullable String userId, @Nullable String name);

    void finishByIndex(@Nullable String userId, @Nullable Integer index);

    void finishById(@Nullable String userId, @Nullable String id);

    void finishByName(@Nullable String userId, @Nullable String name);

    void changeStatusById(
            @Nullable String userId,
            @Nullable String id,
            @NotNull Status status
    );

    void changeStatusByIndex(
            @Nullable String userId, @Nullable Integer index, @Nullable Status status
    );

    void changeStatusByName(
            @Nullable String userId, @Nullable String name, @Nullable Status status
    );

}
