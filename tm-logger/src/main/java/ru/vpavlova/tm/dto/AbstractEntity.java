package ru.vpavlova.tm.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

    @NotNull
    protected String id = UUID.randomUUID().toString();

}