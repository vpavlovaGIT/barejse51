package ru.vpavlova.tm.api;

import ru.vpavlova.tm.entity.AbstractEntity;

import java.util.Collection;
import java.util.List;

public interface IRepository<E extends AbstractEntity> {

    List<E> findAll(String userId);

    E add(E entity);

    void addAll(Collection<E> collection);

    E findOneById(String userId, String id);

    void clear();

    void remove(E entity);

    E removeOneById(String userId, String id);

}
