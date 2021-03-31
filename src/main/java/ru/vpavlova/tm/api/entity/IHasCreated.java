package ru.vpavlova.tm.api.entity;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public interface IHasCreated {

    @NotNull
    Date getCreated();

    void setCreated(@NotNull Date date);

}
