package ru.vpavlova.tm.api;

import ru.vpavlova.tm.entity.AbstractEntity;

import java.util.Collection;
import java.util.List;

public interface IRepository<E extends AbstractEntity> {

    List<E> findAll();

    E add(E entity);

    void addAll(Collection<E> collection);

    E findOneById(String id);

    E findOneByIndex(Integer index);

    void clear();

    void remove(E entity);

    E removeOneById(String id);

    E removeOneByIndex(Integer index);

}
