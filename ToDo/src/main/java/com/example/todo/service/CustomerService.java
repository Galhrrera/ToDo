package com.example.todo.service;

import com.example.todo.model.Customer;
import com.example.todo.repository.CustomerRepository;
import com.example.todo.service.exceptions.CustomerAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(Customer customer){
        Optional<Customer> existingCustomer = customerRepository.findCustomerByUsername((customer.getUsername()));
        if (existingCustomer.isPresent())
            throw new CustomerAlreadyExistsException("A customer with username "+ customer.getUsername() + " already exists");
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}
