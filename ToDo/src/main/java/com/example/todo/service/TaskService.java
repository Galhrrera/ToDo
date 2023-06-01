package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.exceptions.TaskAlreadyExistsException;
import com.example.todo.service.exceptions.TaskDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;



    @Transactional
    public Task createTask(Task task) {
        Optional<Task> existingTask = taskRepository.findTasksByTitle(task.getTitle());
        if (existingTask.isPresent())
            throw new TaskAlreadyExistsException("A task with title "+task.getTitle()+" already exist");
        return taskRepository.save(task);
    }


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


    public void deleteTask(Long taskId) {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskDoesNotExistException("Task with id {" + taskId + "} not found"));
        taskRepository.deleteById(taskId);
    }


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public List<Task> getAllPendingTasks() {
        return taskRepository.findByCompleted(false);
    }


    public List<Task> getAllCompletedTasks() {
        return taskRepository.findByCompleted(true);
    }


/*    public List<Task> getAllTasksByCustomer(Customer customer) {
        return null;
    }


    public List<Task> getAllPendingTasksByCustomer(Customer customer) {
        return null;
    }


    public List<Task> getAllCompletedTasksByCustomer(Customer customer) {
        return null;
    }*/

}
