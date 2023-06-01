package com.example.todo.repository;

import com.example.todo.model.Customer;
import com.example.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);
    Optional<Task> findTasksByTitle(String title);
/*    Task createTask(Task task);
    Task updateTask(Long taskId, Task task);
    void deleteTask(Long taskId);
    List<Task> getAllTasks();
    List<Task> getAllPendingTasks();
    List<Task> getAllCompletedTasks();
    List<Task> getAllTasksByCustomer(Customer customer);
    List<Task> getAllPendingTasksByCustomer(Customer customer);
    List<Task> getAllCompletedTasksByCustomer(Customer customer);*/

}
