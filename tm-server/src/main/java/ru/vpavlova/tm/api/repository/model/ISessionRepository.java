package ru.vpavlova.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.Session;

import java.util.List;
import java.util.Optional;

public interface ISessionRepository extends IRepository<Session> {

    void clear();

    @NotNull
    List<Session> findAll();

    @NotNull
    Optional<Session> findById(@Nullable String id);

    void removeOneById(@Nullable String id);

}

