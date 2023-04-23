package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task with id {" + taskId + "} not found"));
        existingTask.setTitle(task.getTitle());;
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());

        return taskRepository.save(existingTask)
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllPendingTasks() {
        return taskRepository.findByCompleted(false);
    }

    @Override
    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompleted(true);
    }
}
