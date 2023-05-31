package com.example.todo.service;

import com.example.todo.model.Customer;
import com.example.todo.repository.CustomerRepository;
import com.example.todo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Customer customer = customerRepository.findCustomerByUsername(username)
                 .orElseThrow(() -> new UsernameNotFoundException("User "+username+ "does not exist"));

         return new UserDetailsImpl(customer);
    }
}
