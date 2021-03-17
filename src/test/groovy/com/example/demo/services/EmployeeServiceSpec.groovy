package com.example.demo.services

import com.example.demo.domains.EmployeeDetail
import com.example.demo.models.Employee
import com.example.demo.repositories.EmployeeRepository
import org.junit.jupiter.api.Assertions
import org.spockframework.spring.SpringBean
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

class EmployeeServiceSpec extends Specification {

    @SpringBean
    EmployeeRepository employeeRepository = Stub()
    EmployeeService employeeService

    void setup() {
        this.employeeService = new EmployeeService(employeeRepository)
    }

    void cleanup() {
        this.employeeService = null
    }

    def "Should add employee details"() {

        given:
        Employee employee = new Employee('Tom', 'Developer')
        employeeRepository.insert(_ as EmployeeDetail) >> Mono.just(new EmployeeDetail())

        when:
        Mono<EmployeeDetail> employeeDetail = employeeService.addEmployeeDetails(10001, employee)

        then:
        StepVerifier.create(employeeDetail)
                .consumeNextWith(Assertions.&assertNotNull)
                .verifyComplete()
    }
}
