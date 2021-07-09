package ru.vpavlova.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IRepository;
import ru.vpavlova.tm.dto.Session;

import java.util.List;
import java.util.Optional;

public interface ISessionRepository extends IRepository<Session> {

    void clear();

    @NotNull
    List<Session> findAll();

    @NotNull
    Optional<Session> findOneById(@Nullable String id);

    void removeOneById(@Nullable String id);

}
