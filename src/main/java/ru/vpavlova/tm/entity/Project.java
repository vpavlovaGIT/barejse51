package ru.vpavlova.tm.entity;

import ru.vpavlova.tm.api.entity.IWBS;

public final class Project extends AbstractBusinessEntity implements IWBS {

    public Project() {
    }

    public Project(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

}
