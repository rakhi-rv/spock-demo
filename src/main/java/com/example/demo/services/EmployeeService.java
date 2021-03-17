package com.example.demo.services;

import com.example.demo.domains.EmployeeDetail;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Mono<EmployeeDetail> addEmployeeDetails(Integer employeeId, Employee employee) {
        return employeeRepository.insert(getEmployeeDetail(employeeId, employee));
    }

    private EmployeeDetail getEmployeeDetail(Integer employeeId, Employee employee) {
        return EmployeeDetail.builder()
                .employeeId(employeeId)
                .name(employee.getName())
                .designation(employee.getDesignation())
                .build();
    }
}
