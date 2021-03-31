package ru.vpavlova.tm.api.entity;

import org.jetbrains.annotations.NotNull;

public interface IHasName {

    @NotNull
    String getName();

    void setName(@NotNull String name);

}
