package ru.vpavlova.tm.api.entity;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.enumerated.Status;

public interface IHasStatus {

    @NotNull
    Status getStatus();

    void setStatus(@NotNull Status status);

}
