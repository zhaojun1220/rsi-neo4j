package com.rsi.neo4j.nodeentity;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Player extends BaseEntity{
    private double height;
    private String location;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
