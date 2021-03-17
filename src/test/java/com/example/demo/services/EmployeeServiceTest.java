package com.example.demo.services;

import com.example.demo.domains.EmployeeDetail;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        this.employeeService = new EmployeeService(employeeRepository);
    }

    @AfterEach
    void tearDown() {
        this.employeeService = null;
    }

    @Test
    void addEmployeeDetails() {
        Employee employee = new Employee("Tom", "Developer");
        when(employeeRepository.insert(ArgumentMatchers.any(EmployeeDetail.class)))
                .thenReturn(Mono.just(new EmployeeDetail()));
        Mono<EmployeeDetail> employeeDetail = employeeService.addEmployeeDetails(10001, employee);
        StepVerifier.create(employeeDetail)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }
}