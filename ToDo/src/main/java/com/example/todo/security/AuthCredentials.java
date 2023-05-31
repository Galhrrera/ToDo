package com.example.todo.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String username;
    private String password;
}
