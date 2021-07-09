package ru.vpavlova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.entity.AbstractGraphEntity;

import javax.persistence.TypedQuery;
import java.util.Optional;

public interface IGraphRepository<E extends AbstractGraphEntity> {

    void add(@NotNull E entity);

    Optional<E> getSingleResult(@NotNull TypedQuery<E> query);

    void update(E entity);

}
