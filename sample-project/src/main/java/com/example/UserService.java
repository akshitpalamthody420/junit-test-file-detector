package com.example;

public class UserService {
    public boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}
