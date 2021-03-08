package ru.vpavlova.tm.entity;

import ru.vpavlova.tm.api.entity.IWBS;

public final class Task extends AbstractBusinessEntity implements IWBS {

    private String projectId;

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

}
