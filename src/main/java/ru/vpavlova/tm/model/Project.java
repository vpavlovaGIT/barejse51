package ru.vpavlova.tm.model;

import ru.vpavlova.tm.enumerated.Status;
import java.util.UUID;

public class Project {

    private String id = UUID.randomUUID().toString();

    private String name = "";

    private String description = "";

    private Status status = Status.NOT_STARTED;

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
        return id + ": " + name;
    }

}
