package com.example.demo.controllers;

import com.example.demo.domains.EmployeeDetail;
import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest
@ContextConfiguration(classes = {EmployeeController.class})
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ApplicationContext context;
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context)
                .build();
    }

    @AfterEach
    void tearDown() {
        webTestClient = null;
    }

    @Test
    void createEmployee() {

        Employee employee = new Employee("Tom", "Developer");
        when(employeeService.addEmployeeDetails(10001, employee))
                .thenReturn(Mono.just(new EmployeeDetail()));

        webTestClient.post()
                .uri("/employee/10001")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(employee))
                .exchange()
                .expectStatus()
                .isCreated();
    }
}