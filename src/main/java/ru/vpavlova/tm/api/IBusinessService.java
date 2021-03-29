package ru.vpavlova.tm.api;

import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.enumerated.Status;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface IBusinessService<E extends AbstractBusinessEntity> extends IService<E> {

    List<E> findAll(String userId);

    List<E> findAll(String userId, Comparator<E> comparator);

    E add(String userId, E entity);

    Optional<E> findById(String userId, String id);

    Optional<E> findByIndex(String userId, Integer index);

    Optional<E> findByName(String userId, String name);

    void clear(String userId);

    Optional<E> updateByIndex(String userId, Integer index, String name, String description);

    Optional<E> updateById(String userId, String id, String name, String description);

    Optional<E> startByIndex(String userId, Integer index);

    Optional<E> startById(String userId, String id);

    Optional<E> startByName(String userId, String name);

    Optional<E> finishByIndex(String userId, Integer index);

    Optional<E> finishById(String userId, String id);

    Optional<E> finishByName(String userId, String name);

    Optional<E> changeStatusByIndex(String userId, Integer index, Status status);

    Optional<E> changeStatusById(String userId, String id, Status status);

    Optional<E> changeStatusByName(String userId, String name, Status status);

    void remove(String userId, E entity);

    E removeById(String userId, String id);

    E removeByIndex(String userId, Integer index);

    E removeByName(String userId, String name);

}
