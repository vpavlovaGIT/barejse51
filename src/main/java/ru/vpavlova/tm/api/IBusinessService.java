package ru.vpavlova.tm.api;

import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.enumerated.Status;

import java.util.List;
import java.util.Optional;

public interface IBusinessService<E extends AbstractBusinessEntity> extends IService<E> {

    List<E> findAll(String userId);

    E add(String userId, E entity);

    Optional<E> findOneById(String userId, String id);

    Optional<E> findOneByIndex(String userId, Integer index);

    Optional<E> findOneByName(String userId, String name);

    void clear(String userId);

    E removeOneById(String userId, String id);

    E removeOneByIndex(String userId, Integer index);

    E removeOneByName(String userId, String name);

    void remove(String userId, E entity);

    Optional<E> updateOneById(String userId, String id, String name, String description);

    Optional<E> updateOneByIndex(String userId, Integer index, String name, String description);

    Optional<E> startOneById(String userId, String id);

    Optional<E> startOneByIndex(String userId, Integer index);

    Optional<E> startOneByName(String userId, String name);

    Optional<E> finishOneById(String userId, String id);

    Optional<E> finishOneByIndex(String userId, Integer index);

    Optional<E> finishOneByName(String userId, String name);

    Optional<E> changeOneStatusById(String userId, String id, Status status);

    Optional<E> changeOneStatusByIndex(String userId, Integer index, Status status);

    Optional<E> changeOneStatusByName(String userId, String name, Status status);

}
