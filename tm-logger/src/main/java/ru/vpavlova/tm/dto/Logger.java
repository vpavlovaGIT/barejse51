package ru.vpavlova.tm.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.enumerated.OperationType;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Logger extends AbstractEntity implements Serializable {

    @NotNull
    private Date created = new Date();

    @Nullable
    private String entity;

    @Nullable
    private String className;

    @Nullable
    private OperationType operation;

}
