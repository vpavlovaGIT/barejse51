package ru.vpavlova.tm.api.entity;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public interface IHasDateStart {

    @NotNull
    Date getDateStart();

    void setDateStart(@NotNull Date dateStart);

}
