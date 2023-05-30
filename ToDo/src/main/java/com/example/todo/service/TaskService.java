package com.example.todo.service;

import com.example.todo.model.Customer;
import com.example.todo.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Long taskId, Task task);
    void deleteTask(Long taskId);
    List<Task> getAllTasks();
    List<Task> getAllPendingTasks();
    List<Task> getAllCompletedTasks();
    List<Task> getAllTasksByCustomer(Customer customer);
    List<Task> getAllPendingTasksByCustomer(Customer customer);
    List<Task> getAllCompletedTasksByCustomer(Customer customer);
}
