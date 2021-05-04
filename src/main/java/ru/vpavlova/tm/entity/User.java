package ru.vpavlova.tm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.enumerated.Role;

@Getter
@Setter
@NoArgsConstructor
public final class User extends AbstractEntity {

    @NotNull
    private String login;

    @NotNull
    private String passwordHash;

    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String middleName;

    @NotNull
    private Role role = Role.USER;

    private boolean locked = false;

}
