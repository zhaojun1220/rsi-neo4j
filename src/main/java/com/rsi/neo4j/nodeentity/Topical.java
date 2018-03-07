package com.rsi.neo4j.nodeentity;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Topical extends BaseEntity {

    public Topical(){}
    public Topical(String stdno) {
        this.stdNo = stdno;
    }

//    private int stdId;
    private String stdNo;
//    private String stdName;

    @Relationship(type = "引用", direction = Relationship.UNDIRECTED)
    public Set<Topical> quotes;

//    public int getStdId() {
//        return stdId;
//    }
//
//    public void setStdId(int stdId) {
//        this.stdId = stdId;
//    }

    public String getStdNo() {
        return stdNo;
    }

    public void setStdNo(String stdNo) {
        this.stdNo = stdNo;
    }

//    public String getStdName() {
//        return stdName;
//    }
//
//    public void setStdName(String stdName) {
//        this.stdName = stdName;
//    }

    /**
     * 引用关系
     * @param topical
     */
    public void quoteWith(Topical topical) {
        if (quotes == null) {
            quotes = new HashSet<>();
        }
        quotes.add(topical);
    }
}
