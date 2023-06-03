package com.example.todo.authentication;

import com.example.todo.model.Customer;
import com.example.todo.repository.CustomerRepository;
import com.example.todo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest request) {
        var customer = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        customerRepository.save(customer);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
    }
}
