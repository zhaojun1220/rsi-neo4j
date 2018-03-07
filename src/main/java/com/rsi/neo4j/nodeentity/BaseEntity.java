package com.rsi.neo4j.nodeentity;

import org.neo4j.ogm.annotation.GraphId;

/**
 * 抽取共同的属性字段
 */
public abstract class BaseEntity {

    /** Neo4j会分配的ID（节点唯一标识 当前类中有效）*/
    @GraphId
    protected Long id;
    protected String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
