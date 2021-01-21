package ru.vpavlova.tm.model;

import ru.vpavlova.tm.enumerated.Status;
import java.util.UUID;

public class Task {

    private String id = UUID.randomUUID().toString();

    private String name = "";

    private String description = "";

    private String projectId;

    private Status status = Status.NOT_STARTED;

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
