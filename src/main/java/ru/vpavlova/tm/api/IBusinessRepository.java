package ru.vpavlova.tm.api;

import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.enumerated.Status;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface IBusinessRepository<E extends AbstractBusinessEntity> extends IRepository<E> {

    List<E> findAll(String userId);

    List<E> findAll(String userId, Comparator<E> comparator);

    E add(String userId, E entity);

    Optional<E> findById(String userId, String id);

    Optional<E> findByIndex(String userId, Integer index);

    Optional<E> findByName(String userId, String name);

    void clear(String userId);

    E removeById(String userId, String id);

    E removeByIndex(String userId, Integer index);

    E removeByName(String userId, String name);

    void remove(String userId, E entity);

}
