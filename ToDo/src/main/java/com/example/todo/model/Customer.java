package com.example.todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@NoArgsConstructor //getters, setters and no args constructor from lombok. Look imports
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "username is mandatory")
    @Length(min = 3)
    private String username;
    @NotBlank(message = "password is mandatory")
    @Length(min = 8, message = "password length must be min 8 characters")
    private String password;
}
