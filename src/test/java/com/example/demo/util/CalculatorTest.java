package com.example.demo.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CalculatorTest {

    @DisplayName("Should multiply two numbers")
    @Test
    void shouldMultiplyTwoNumbers() {
        //given
        Calculator calculator = new Calculator();

        //when
        int result = calculator.multiply(2, 3);

        //then
        Assertions.assertEquals(6, result);
    }

    @ParameterizedTest(name = "multiply {0} and {1} should give {2}")
    @MethodSource("integersProvider")
    void shouldMultiplyTwoNumbers(int num1, int num2, int expectedResult) {
        //given
        Calculator calculator = new Calculator();
        //when
        int result = calculator.multiply(num1, num2);
        //then
        Assertions.assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> integersProvider() {
        return Stream.of(Arguments.of(2, 2, 4),
                Arguments.of(2, 3, 6),
                Arguments.of(2, -2, -4));
    }

    @DisplayName("Should find maximum numbers")
    @Test
    void shouldFindMaximum() {
        //then
        Assertions.assertEquals(2, Math.max(2,4));
    }

    @Test
    void exception() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            String name = null;
            name.length();
        });
    }
}
