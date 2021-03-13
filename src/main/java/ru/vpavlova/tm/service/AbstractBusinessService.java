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
import java.util.Optional;

public abstract class AbstractBusinessService<E extends AbstractBusinessEntity> extends AbstractService<E> implements IBusinessService<E> {

    private final IBusinessRepository<E> repository;

    public AbstractBusinessService(IBusinessRepository<E> repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<E> findAll(final String userId) {
        if (!Optional.ofNullable(userId).isPresent()) return null;
        return repository.findAll(userId);
    }

    @Override
    public E add(final String userId, final E entity) {
        if (!Optional.ofNullable(userId).isPresent()) return null;
        if (!Optional.ofNullable(entity).isPresent()) throw new ObjectNotFoundException();
        return repository.add(userId, entity);
    }

    @Override
    public Optional<E> findOneById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return repository.findOneById(userId, id);
    }

    @Override
    public Optional<E> findOneByIndex(final String userId, Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        return repository.findOneByIndex(userId, index);
    }

    @Override
    public Optional<E> findOneByName(final String userId, final String name) {
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
        if (!Optional.ofNullable(entity).isPresent()) throw new ObjectNotFoundException();
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
    public Optional<E> updateOneById(final String userId, final String id, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Optional<E> entity = findOneById(userId, id);
        entity.ifPresent(e -> {
            e.setId(id);
            e.setName(name);
            e.setDescription(description);
        });
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @Override
    public Optional<E> updateOneByIndex(final String userId, final Integer index, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Optional<E> entity = findOneByIndex(userId, index);
        entity.ifPresent(e -> {
            e.setName(name);
            e.setDescription(description);
        });
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @Override
    public Optional<E> startOneById(final String userId, String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Optional<E> entity = findOneById(userId, id);
        entity.ifPresent(e -> e.setStatus(Status.IN_PROGRESS));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @Override
    public Optional<E> startOneByIndex(final String userId, Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();final Optional<E> entity = findOneByIndex(userId, index);
        entity.ifPresent(e -> e.setStatus(Status.IN_PROGRESS));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    public Optional<E> startOneByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Optional<E> entity = findOneByName(userId, name);
        entity.ifPresent(e -> e.setStatus(Status.IN_PROGRESS));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    public Optional<E> finishOneById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Optional<E> entity = findOneById(userId, id);
        entity.ifPresent(e -> e.setStatus(Status.COMPLETE));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;

    }

    @Override
    public Optional<E> finishOneByIndex(final String userId, final Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Optional<E> entity = findOneByIndex(userId, index);
        entity.ifPresent(e -> e.setStatus(Status.COMPLETE));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    public Optional<E> finishOneByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Optional<E> entity = findOneByName(userId, name);
        entity.ifPresent(e -> e.setStatus(Status.COMPLETE));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @Override
    public Optional<E> changeOneStatusById(final String userId, String id, Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Optional<E> entity = findOneById(userId, id);
        entity.ifPresent(e -> e.setStatus(status));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @Override
    public Optional<E> changeOneStatusByIndex(final String userId, Integer index, Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Optional<E> entity = findOneByIndex(userId, index);
        entity.ifPresent(e -> e.setStatus(status));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;
    }

    @Override
    public Optional<E> changeOneStatusByName(final String userId, String name, Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyUserIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Optional<E> entity = findOneByName(userId, name);
        entity.ifPresent(e -> e.setStatus(status));
        entity.orElseThrow(ObjectNotFoundException::new);
        return entity;

    }

}
