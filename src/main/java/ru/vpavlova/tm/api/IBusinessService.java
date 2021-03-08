package ru.vpavlova.tm.api;

import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.enumerated.Status;

import java.util.List;

public interface IBusinessService<E extends AbstractBusinessEntity> extends IService<E> {

    List<E> findAll(String userId);

    E add(String userId, E entity);

    E findOneById(String userId, String id);

    E findOneByIndex(String userId, Integer index);

    E findOneByName(String userId, String name);

    void clear(String userId);

    E removeOneById(String userId, String id);

    E removeOneByIndex(String userId, Integer index);

    E removeOneByName(String userId, String name);

    void remove(String userId, E entity);

    E updateOneById(String userId, String id, String name, String description);

    E updateOneByIndex(String userId, Integer index, String name, String description);

    E startOneById(String userId, String id);

    E startOneByIndex(String userId, Integer index);

    E startOneByName(String userId, String name);

    E finishOneById(String userId, String id);

    E finishOneByIndex(String userId, Integer index);

    E finishOneByName(String userId, String name);

    E changeOneStatusById(String userId, String id, Status status);

    E changeOneStatusByIndex(String userId, Integer index, Status status);

    E changeOneStatusByName(String userId, String name, Status status);

}