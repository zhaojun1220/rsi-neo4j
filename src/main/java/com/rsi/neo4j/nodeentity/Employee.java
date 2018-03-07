package com.rsi.neo4j.nodeentity;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NodeEntity
public class Employee extends BaseEntity {

    private Employee(){}
    public Employee(String name) {
        this.name = name;
    }
    /**
     * Neo4j 并没有真正的双向关系，我们只有在查询的时候忽略关系的方向
     */
    @Relationship(type = "同事", direction = Relationship.UNDIRECTED)
    public Set<Employee> colleagues;

    @Relationship(type = "管理")
    public Set<Employee> manages;

    /**
     * 指定同事关系
     * @param person
     */
    public void worksWith(Employee person) {
        if (colleagues == null) {
            colleagues = new HashSet<>();
        }
        colleagues.add(person);
    }

    /**
     * 指定管理关系
     * @param person
     */
    public void management(Employee person) {
        if (manages == null) {
            manages = new HashSet<>();
        }
        manages.add(person);
    }

    /**
     * 列出该节点（Employee）的关系网
     * @return
     */
    public String toString() {
        /* java8新特新
         * Optional.ofNullable(arg) 参数可以是null
         * 如果值不为null，orElse方法返回Optional实例（关系）的值
         * Collections.emptySet():防止空指针出现
         *            |
         *            |
         *            V
         * 当代码需要一个集合而这个集合可能不存在，此时尽量使用空集合而不是null
         */
        return this.name + " 同事 => "
                + Optional.ofNullable(this.colleagues).orElse(
                Collections.emptySet()).stream().map(
                person -> person.getName()).collect(Collectors.toList())
                + " 管理 => "
                + Optional.ofNullable(this.manages).orElse(
                Collections.emptySet()).stream().map(
                person -> person.getName()).collect(Collectors.toList());
    }
}
