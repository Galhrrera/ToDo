package com.example.todo.service.exceptions;

public class TaskAlreadyExistsException extends RuntimeException{
    public TaskAlreadyExistsException(String message) {
        super(message);
    }
}
