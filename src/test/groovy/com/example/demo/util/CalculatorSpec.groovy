package com.example.demo.util

import spock.lang.Specification
import spock.lang.Unroll

class CalculatorSpec extends Specification {

    def "Should multiply two numbers"() {
        given:
        Calculator calculator = new Calculator()

        when:
        int result = calculator.multiply(2, 2)

        then:
        result == 4
    }

    @Unroll
    def "Should multiply two numbers (#num1 * #num2 = #expectedResult)"() {
        given:
        Calculator calculator = new Calculator()

        when:
        int result = calculator.multiply(num1, num2)

        then:
        result == expectedResult

        where:
        num1 | num2 | expectedResult
        2    | 2    | 4
        2    | 3    | 6
        2    | -2   | -4
    }

    @Unroll
    def "Should find maximum of two numbers (#num1 and #num2 = #expectedResult)"() {
        expect:
        Math.max(num1, num2) == expectedResult

        where:
        num1 | num2 | expectedResult
        2    | 4    | 2
    }

    def "Exception Handling"() {
        when:
        String name = null
        name.length()

        then:
        thrown(NullPointerException)
    }

}

