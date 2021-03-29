package ru.vpavlova.tm.api;

import ru.vpavlova.tm.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface IRepository<E extends AbstractEntity> {

    List<E> findAll();

    E add(E entity);

    Optional<E> findById(String id);

    void clear();

    E removeById(String id);

    void remove(E entity);

}
