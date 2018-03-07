package com.rsi.neo4j.nodeentity;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Cat extends BaseEntity{
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
