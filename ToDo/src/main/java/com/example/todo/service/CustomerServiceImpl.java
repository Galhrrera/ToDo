package com.example.todo.service;

import com.example.todo.model.Customer;
import com.example.todo.repository.CustomerRepository;
import com.example.todo.service.exceptions.CustomerAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        Optional<Customer> newCustomer = customerRepository.findCustomerByUsername(customer.getUsername());
        if (newCustomer.isPresent())
            throw new CustomerAlreadyExistsException("A customer with username "+ customer.getUsername()+" already exists");
        return customerRepository.save(customer);
    }
}
