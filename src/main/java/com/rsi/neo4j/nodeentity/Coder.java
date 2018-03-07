package com.rsi.neo4j.nodeentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Neo4j的节点实体类：Coder
 */
@NodeEntity
public class Coder extends BaseEntity {
    private String sex ;
    private String hobby;

    @Relationship(type = "Like")
    @JsonProperty("喜欢")
    private List<Player> players;

    @Relationship(type = "Have")
    @JsonProperty("拥有")
    private List<Cat> cats;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }
}
