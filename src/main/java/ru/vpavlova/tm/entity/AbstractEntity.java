package ru.vpavlova.tm.entity;

import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractEntity implements Serializable {

    private String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
