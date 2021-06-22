package ru.vpavlova.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserDTORepository extends IRepositoryDTO<UserDTO> {

    void clear();

    @NotNull
    List<UserDTO> findAll();

    @NotNull
    Optional<UserDTO> findByLogin(@NotNull String login);

    @NotNull
    Optional<UserDTO> findById(@Nullable String id);

    void removeByLogin(@NotNull String login);

    void removeOneById(@Nullable String id);

}
