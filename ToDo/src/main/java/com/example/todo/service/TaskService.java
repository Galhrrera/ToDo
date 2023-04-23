package com.example.todo.service;

import com.example.todo.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Long taskId, Task task);
    void deleteTask(Long taskId);
    List<Task> getAllTasks();
    List<Task> getAllPendingTasks();
    List<Task> getAllCompletedTasks();
}
