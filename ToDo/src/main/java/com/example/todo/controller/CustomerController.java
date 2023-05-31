package com.example.todo.controller;

import com.example.todo.model.Customer;
import com.example.todo.model.Task;
import com.example.todo.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
@RequestMapping("api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Create new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error - Customer already exist (usually for duplicated customer's name)")})
    @PostMapping
    public ResponseEntity<Customer> createTask(@Valid @RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        logger.info("Customer with id " + createdCustomer.getId() + " created");
        return new ResponseEntity<>(createdCustomer, HttpStatus.OK);
    }
}