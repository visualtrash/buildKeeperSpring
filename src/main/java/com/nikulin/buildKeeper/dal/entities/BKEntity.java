package com.nikulin.buildKeeper.dal.entities;

import java.util.UUID;

public abstract class BKEntity {
    private UUID id = UUID.randomUUID();
    private String name;

    BKEntity(String name) {
        this.name = name;
    }

    BKEntity() {

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
