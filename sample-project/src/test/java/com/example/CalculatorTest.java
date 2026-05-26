package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
    @Test
    void addsTwoNumbers() {
        Calculator calculator = new Calculator();
        assertEquals(4, calculator.add(2, 2));
    }

    @Test
    void dividesTwoNumbers() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.divide(10, 2));
    }
}
