package ru.vpavlova.tm.api;

import ru.vpavlova.tm.entity.AbstractBusinessEntity;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface IBusinessRepository<E extends AbstractBusinessEntity> extends IRepository<E> {

    List<E> findAll(String userId);

    List<E> findAll(Comparator<E> comparator);

    E add(String userId, E entity);

    void addAll(String userId, Collection<E> collection);

    E findOneById(String userId, String id);

    E findOneByIndex(String userId, Integer index);

    E findOneByName(String userId, String name);

    void clear(String userId);

    E removeOneById(String userId, String id);

    E removeOneByIndex(String userId, Integer index);

    E removeOneByName(String userId, String name);

    void remove(String userId, E entity);

}
