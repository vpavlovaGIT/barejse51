package ru.vpavlova.tm.api;

import ru.vpavlova.tm.entity.AbstractEntity;

public interface IService<E extends AbstractEntity> extends IRepository<E> {

}
