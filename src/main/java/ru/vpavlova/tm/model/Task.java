package ru.vpavlova.tm.model;

import java.util.UUID;

public class Task {

    private String id = UUID.randomUUID().toString();

    private String name = "";

    private String description = "";

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
        return id + ": " + name;
    }
}
