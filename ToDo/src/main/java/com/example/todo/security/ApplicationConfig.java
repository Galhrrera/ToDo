package com.example.todo.security;

import com.example.todo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private CustomerRepository customerRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> customerRepository.findCustomerByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
