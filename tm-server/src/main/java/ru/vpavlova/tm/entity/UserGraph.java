package ru.vpavlova.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.listener.LoggerEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_user")
@EntityListeners(LoggerEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserGraph extends AbstractGraphEntity {

    @Column
    @NotNull
    private String login;

    @NotNull
    @Column(name = "password_hash")
    private String passwordHash;

    @Column
    @NotNull
    private String email;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column
    private boolean locked = false;

    @Nullable
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectGraph> projects = new ArrayList<>();

    @Nullable
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionGraph> sessions = new ArrayList<>();

    @Nullable
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskGraph> tasks = new ArrayList<>();

}
