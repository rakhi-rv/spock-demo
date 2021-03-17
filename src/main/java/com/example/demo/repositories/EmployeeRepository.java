package com.example.demo.repositories;

import com.example.demo.domains.EmployeeDetail;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface EmployeeRepository extends ReactiveCassandraRepository<EmployeeDetail, Integer> {
}
