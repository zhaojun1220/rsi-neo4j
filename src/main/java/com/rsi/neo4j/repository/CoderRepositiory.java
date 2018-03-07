package com.rsi.neo4j.repository;

import com.rsi.neo4j.nodeentity.Coder;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Coder 节点增删改
 */
@Repository
public interface CoderRepositiory extends GraphRepository<Coder> {

    Coder findByName(@Param("name") String name);
}
