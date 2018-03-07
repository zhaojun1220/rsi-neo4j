package com.rsi.neo4j.repository;

import com.rsi.neo4j.nodeentity.Cat;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatReponsitory extends GraphRepository<Cat> {
}
