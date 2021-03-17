package com.example.demo.models

import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

@Unroll
class EmployeeSpec extends Specification {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private Validator validator;
    private Employee employee;

    void setup() {
        this.validator = factory.getValidator();
        this.employee = new Employee("Tom", "Developer");
    }

    def "Name is not null"() {
        when:
        employee.setName(null)

        then:
        assert !validator.validate(employee).isEmpty()
    }

    def "Designation #designation is valid"() {
        when:
        employee.setDesignation(designation)

        then:
        assert validator.validate(employee).isEmpty()

        where:
        designation << ['Developer', 'Tester']
    }

}
