package com.example.demo.controllers

import com.example.demo.domains.EmployeeDetail
import com.example.demo.models.Employee
import com.example.demo.services.EmployeeService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Mono
import spock.lang.Specification

@WebFluxTest
@ContextConfiguration(classes = [EmployeeController.class])
class EmployeeControllerSpec extends Specification {

    @SpringBean
    EmployeeService employeeService = Stub()

    @Autowired
    private ApplicationContext context
    private WebTestClient webTestClient

    void setup() {
        webTestClient = WebTestClient.bindToApplicationContext(context)
                .build()
    }

    void cleanup() {
        webTestClient = null
    }

    def "Create Employee"() {

        given :
        Employee employee = new Employee('Tom', 'Developer')
        employeeService.addEmployeeDetails(10001, employee) >> Mono.just(new EmployeeDetail())

        expect :
        webTestClient.post()
                .uri('/employee/10001')
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(employee))
                .exchange()
                .expectStatus()
                .isCreated()
    }
}
