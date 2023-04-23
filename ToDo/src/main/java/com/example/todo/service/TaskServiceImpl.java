package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
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
        if (task.getDescription() != null)
            existingTask.setDescription(task.getDescription());
        if (task.getTitle() != null)
            existingTask.setTitle(task.getTitle());
        if (task.isCompleted() != existingTask.isCompleted())
            existingTask.setCompleted(task.isCompleted());

        return taskRepository.save(existingTask);
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
    public List<Task> getAllCompletedTasks() {
        return taskRepository.findByCompleted(true);
    }
}
