package ru.vpavlova.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

    @Id
    @NotNull
    protected String id = UUID.randomUUID().toString();

}
