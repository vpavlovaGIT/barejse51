package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.dto.UserDTO;
import ru.vpavlova.tm.enumerated.Role;

public interface IUserService extends IService<UserDTO> {

    @NotNull
    UserDTO create(@Nullable String login, @Nullable String password);

    void create(@Nullable String login, @Nullable String password, @Nullable String email);

    @NotNull
    UserDTO create(@Nullable String login, @Nullable String password, @Nullable Role role);

    void setPassword(@Nullable String userId, @Nullable String password);

    @NotNull
    UserDTO findByLogin(@Nullable String login);

    @Nullable
    UserDTO findByEmail(@Nullable String email);

    void updateUser(
            @Nullable String userId,
            @NotNull String firstName,
            @NotNull String lastName,
            @NotNull String middleName
    );

    void removeByLogin(@Nullable String login);

    void lockUserByLogin(@Nullable String login);

    void unlockUserByLogin(@Nullable String login);

    void remove(@Nullable UserDTO entity);

}
