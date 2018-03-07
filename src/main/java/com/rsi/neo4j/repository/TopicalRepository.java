package com.rsi.neo4j.repository;

import com.rsi.neo4j.nodeentity.Topical;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicalRepository extends GraphRepository<Topical> {
    Topical findByStdNo(String name);
}
