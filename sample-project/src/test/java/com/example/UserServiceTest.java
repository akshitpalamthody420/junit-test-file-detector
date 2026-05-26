package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {
    @ParameterizedTest
    @ValueSource(strings = {"a@test.com", "student@manchester.ac.uk"})
    void acceptsValidEmails(String email) {
        UserService service = new UserService();
        assertTrue(service.isValidEmail(email));
    }
}
