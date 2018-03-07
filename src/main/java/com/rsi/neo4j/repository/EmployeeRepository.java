package com.rsi.neo4j.repository;

import com.rsi.neo4j.nodeentity.Employee;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends GraphRepository<Employee> {
    Employee findByName(String name);
}
