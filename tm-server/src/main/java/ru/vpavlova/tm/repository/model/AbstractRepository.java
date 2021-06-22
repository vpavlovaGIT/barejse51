package ru.vpavlova.tm.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    @NotNull
    protected final EntityManager entityManager;

    protected AbstractRepository(@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void add(@Nullable final E entity) {
        if (entity == null) return;
        entityManager.persist(entity);
    }

    @Override
    public void update(@Nullable final E entity) {
        if (entity == null) return;
        entityManager.merge(entity);
    }

    @NotNull
    @Override
    public Optional<E> getSingleResult(@NotNull final TypedQuery<E> query) {
        @NotNull final List<E> list = query.getResultList();
        if (list.isEmpty()) return Optional.empty();
        return Optional.of(list.get(0));
    }

}

