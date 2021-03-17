package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private Validator validator;
    private Employee employee;

    @BeforeEach
    void setUp() {
        this.validator = factory.getValidator();
        this.employee = new Employee("Tom", "Developer");
    }

    @Test
    void employeeNameIsValid() {
        employee.setName("John");
        assertTrue(validator.validate(employee).isEmpty());
    }

    @Test
    void employeeNameIsNull() {
        employee.setName(null);
        assertFalse(validator.validate(employee).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Developer", "Tester"})
    void employeeDesignationIsValid(String designation) {
        employee.setDesignation(designation);
        assertTrue(validator.validate(employee).isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"xxx", "yyy"})
    void employeeDesignationIsInValid(String designation) {
        employee.setDesignation(designation);
        assertFalse(validator.validate(employee).isEmpty());
    }

}