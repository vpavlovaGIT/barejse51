package ru.vpavlova.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.enumerated.Status;

import java.util.Date;

@Getter
@Setter
public abstract class AbstractBusinessEntity extends AbstractEntity {

    @NotNull
    protected String userId;

    @NotNull
    protected String name = "";

    @NotNull
    protected String description = "";

    @NotNull
    protected Status status = Status.NOT_STARTED;

    @Nullable
    protected Date dateStart;

    @Nullable
    protected Date dateFinish;

    @NotNull
    protected Date created = new Date();

    @NotNull
    public String toString() {
        return getId() + ": " + name + "; " + description + ";" + status;
    }

}
