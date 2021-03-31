package ru.vpavlova.tm.api.entity;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public interface IHasDateFinish {

    @NotNull
    Date getDateFinish();

    void setDateFinish(@NotNull Date dateFinish);

}
