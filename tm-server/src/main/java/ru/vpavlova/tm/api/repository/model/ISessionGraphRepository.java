package ru.vpavlova.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IGraphRepository;
import ru.vpavlova.tm.entity.SessionGraph;

import java.util.List;
import java.util.Optional;

public interface ISessionGraphRepository extends IGraphRepository<SessionGraph> {


    void clear();

    @NotNull
    List<SessionGraph> findAll();

    @NotNull
    Optional<SessionGraph> findById(@Nullable String id);

    void removeOneById(@Nullable String id);

}

