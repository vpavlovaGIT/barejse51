package ru.vpavlova.tm.api;

import ru.vpavlova.tm.entity.AbstractBusinessEntity;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface IBusinessRepository<E extends AbstractBusinessEntity> extends IRepository<E> {

    List<E> findAll(String userId);

    List<E> findAll(Comparator<E> comparator);

    E add(String userId, E entity);

    void addAll(String userId, Collection<E> collection);

    Optional<E> findOneById(String userId, String id);

    Optional<E> findOneByIndex(String userId, Integer index);

    Optional<E> findOneByName(String userId, String name);

    void clear(String userId);

    E removeOneById(String userId, String id);

    E removeOneByIndex(String userId, Integer index);

    E removeOneByName(String userId, String name);

    void remove(String userId, E entity);

}
