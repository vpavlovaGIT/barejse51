package ru.vpavlova.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public abstract class AbstractEntity implements Serializable {

    @NotNull
    private String id = UUID.randomUUID().toString();

    @NotNull
    private String userId;

}
