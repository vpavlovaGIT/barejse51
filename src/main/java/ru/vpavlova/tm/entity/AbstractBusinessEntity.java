package ru.vpavlova.tm.entity;

import ru.vpavlova.tm.enumerated.Status;

import java.util.Date;

public abstract class AbstractBusinessEntity extends AbstractEntity {

    protected String userId;

    protected String name = "";

    protected String description = "";

    protected Status status = Status.NOT_STARTED;

    protected Date dateStart;

    protected Date dateFinish;

    protected Date created = new Date();

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String toString() {
        return getId() + ": " + name + "; " + description + ";" + status;
    }

}
