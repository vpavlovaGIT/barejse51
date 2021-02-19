package ru.vpavlova.tm.entity;

import ru.vpavlova.tm.api.entity.IWBS;
import ru.vpavlova.tm.enumerated.Status;

import java.util.Date;
import java.util.UUID;

public class Task implements IWBS {

    private String id = UUID.randomUUID().toString();

    private String name = "";

    private String description = "";

    private String projectId;

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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Task() {
    }

    public Task(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return id + ": " + name + "; " + description + ";" + status + "; project id: " + projectId;
    }

}