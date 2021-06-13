package ru.vpavlova.tm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.enumerated.Role;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_user")
public class UserDTO extends AbstractEntityDTO {

    @Column
    @NotNull
    private String login;

    @Column
    @NotNull
    private String passwordHash;

    @Column
    @NotNull
    private String email;

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String lastName;

    @Column
    @NotNull
    private String middleName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column
    private boolean locked = false;

}
