package ru.vpavlova.tm.entity;

import ru.vpavlova.tm.api.entity.IWBS;
import ru.vpavlova.tm.enumerated.Status;

import java.util.Date;

public final class Project extends AbstractEntity implements IWBS {

    private String name = "";

    private String description = "";

    private Status status = Status.NOT_STARTED;

    private Date dateStart;

    private Date dateFinish;

    private Date created = new Date();

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    @Override
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

    @Override
    public String toString() {
        return getId() + ": " + name;
    }

}
