package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.api.IBusinessService;
import ru.vpavlova.tm.entity.AbstractBusinessEntity;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.exception.empty.EmptyUserIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;

import java.util.Date;
import java.util.List;

public abstract class AbstractBusinessService<E extends AbstractBusinessEntity> extends AbstractService<E> implements IBusinessService<E> {

    private final IBusinessRepository<E> repository;

    public AbstractBusinessService(IBusinessRepository<E> repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<E> findAll(final String userId) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        return repository.findAll(userId);
    }

    @Override
    public E add(final String userId, final E entity) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (entity == null) throw new ObjectNotFoundException();
        return repository.add(userId, entity);
    }

    @Override
    public E findOneById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return repository.findOneById(userId, id);
    }

    @Override
    public E findOneByIndex(final String userId, Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        return repository.findOneByIndex(userId, index);
    }

    @Override
    public E findOneByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        return repository.findOneByName(userId, name);
    }

    @Override
    public void clear(final String userId) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        repository.clear(userId);
    }

    @Override
    public void remove(final String userId, final E entity) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (entity == null) throw new ObjectNotFoundException();
        repository.remove(userId, entity);
    }

    @Override
    public E removeOneById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return repository.removeOneById(userId, id);
    }

    @Override
    public E removeOneByIndex(final String userId, final Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        return repository.removeOneByIndex(userId, index);
    }

    @Override
    public E removeOneByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        return repository.removeOneByName(userId, name);
    }

    @Override
    public E updateOneById(final String userId, final String id, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final E entity = findOneById(userId, id);
        if (entity == null) return null;
        entity.setId(id);
        entity.setName(name);
        entity.setDescription(description);
        return entity;
    }

    @Override
    public E updateOneByIndex(final String userId, final Integer index, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final E entity = findOneByIndex(userId, index);
        if (entity == null) return null;
        entity.setName(name);
        entity.setDescription(description);
        return entity;
    }

    @Override
    public E startOneById(final String userId, String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final E entity = findOneById(userId, id);
        if (entity == null) return null;
        entity.setStatus(Status.IN_PROGRESS);
        entity.setDateStart(new Date());
        return entity;
    }

    @Override
    public E startOneByIndex(final String userId, Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final E entity = findOneByIndex(userId, index);
        if (entity == null) return null;
        entity.setStatus(Status.IN_PROGRESS);
        entity.setDateStart(new Date());
        return entity;
    }

    public E startOneByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final E entity = findOneByName(userId, name);
        if (entity == null) return null;
        entity.setStatus(Status.IN_PROGRESS);
        return entity;
    }

    public E finishOneById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final E entity = findOneById(userId, id);
        if (entity == null) return null;
        entity.setStatus(Status.COMPLETE);
        return entity;
    }

    @Override
    public E finishOneByIndex(final String userId, final Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final E entity = findOneByIndex(userId, index);
        if (entity == null) return null;
        entity.setStatus(Status.COMPLETE);
        return entity;
    }

    public E finishOneByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final E entity = findOneByName(userId, name);
        if (entity == null) return null;
        entity.setStatus(Status.COMPLETE);
        return entity;
    }

    @Override
    public E changeOneStatusById(final String userId, String id, Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final E entity = findOneById(userId, id);
        if (entity == null) return null;
        entity.setStatus(status);
        if (status == Status.IN_PROGRESS) entity.setDateStart(new Date());
        if (status == Status.COMPLETE) entity.setDateFinish(new Date());
        return entity;
    }

    @Override
    public E changeOneStatusByIndex(final String userId, Integer index, Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final E entity = findOneByIndex(userId, index);
        if (entity == null) return null;
        entity.setStatus(status);
        if (status == Status.IN_PROGRESS) entity.setDateStart(new Date());
        if (status == Status.COMPLETE) entity.setDateFinish(new Date());
        return entity;
    }

    @Override
    public E changeOneStatusByName(final String userId, String name, Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final E entity = findOneByName(userId, name);
        if (entity == null) return null;
        entity.setStatus(status);
        if (status == Status.IN_PROGRESS) entity.setDateStart(new Date());
        if (status == Status.COMPLETE) entity.setDateFinish(new Date());
        return entity;
    }

}
