package ru.vpavlova.tm.api;

import ru.vpavlova.tm.entity.AbstractEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IRepository<E extends AbstractEntity> {

    List<E> findAll(String userId);

    E add(E entity);

    void addAll(Collection<E> collection);

    Optional<E> findOneById(String userId, String id);

    void clear();

    void remove(E entity);

    E removeOneById(String userId, String id);

}
