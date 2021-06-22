package ru.vpavlova.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.SessionDTO;

import java.util.List;
import java.util.Optional;

public interface ISessionDTORepository extends IRepositoryDTO<SessionDTO> {

    void clear();

    @NotNull
    List<SessionDTO> findAll();

    @NotNull
    Optional<SessionDTO> findOneById(@Nullable String id);

    void removeOneById(@Nullable String id);

}
