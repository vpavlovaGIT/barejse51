package ru.vpavlova.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.entity.AbstractEntity;

import javax.persistence.TypedQuery;
import java.util.Optional;

public interface IRepository<E extends AbstractEntity> {

    void add(@NotNull E entity);

    Optional<E> getSingleResult(@NotNull TypedQuery<E> query);

    void update(E entity);

}
