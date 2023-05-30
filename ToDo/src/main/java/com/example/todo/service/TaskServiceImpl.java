package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.exceptions.TaskAlreadyExistsException;
import com.example.todo.service.exceptions.TaskDoesNotExistException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public Task createTask(Task task) {
        Optional<Task> existingTask = taskRepository.findTasksByTitle(task.getTitle());
        if (existingTask.isPresent())
            throw new TaskAlreadyExistsException("A task with title "+task.getTitle()+" already exist");
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskDoesNotExistException("Task with id {" + taskId + "} not found"));
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
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskDoesNotExistException("Task with id {" + taskId + "} not found"));
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
