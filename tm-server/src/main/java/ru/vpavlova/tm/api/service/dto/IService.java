package ru.vpavlova.tm.api.service.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface IService<E extends AbstractEntity> {

    void add(@NotNull E entity);

    void addAll(@NotNull List<E> entities);

    void clear();

    @NotNull
    List<E> findAll();

    @NotNull
    Optional<E> findOneById(@Nullable String id);

    void removeOneById(@Nullable String id);

}
