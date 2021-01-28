package ru.vpavlova.tm.api.entity;

import ru.vpavlova.tm.enumerated.Status;

public interface IHasStatus {

    Status getStatus();

    void setStatus(Status status);

}
