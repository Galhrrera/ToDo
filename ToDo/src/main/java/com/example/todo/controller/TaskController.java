package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    /*private final TaskServiceInterface taskServiceInterface;*/
    private final TaskService taskService;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Create new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request - some incomplete or null fields (title or description)",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error - Task already exist (usually for duplicated task's name)")})
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        logger.info("Task with id " + createdTask.getId() + " created");
        return new ResponseEntity<>(createdTask, HttpStatus.OK);
    }

    @Operation(summary = "Update a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request - some incomplete or null fields (title or description)",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error - Task does not exist")})
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long taskId, @Valid @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(taskId, task);
        logger.info("Task with id " + updatedTask.getId() + " updated");
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @Operation(summary = "Delete a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error - Task does not exist")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long taskId) {
        taskService.deleteTask(taskId);
        logger.info("Task with id " + taskId + " deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found tasks",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tasks not found",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        logger.info("All tasks consulted");
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Operation(summary = "Get all pending tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found pending tasks",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tasks not found",
                    content = @Content) })
    @GetMapping("/pending")
    public ResponseEntity<List<Task>> getPendingTasks() {
        List<Task> tasks = taskService.getAllPendingTasks();
        logger.info("All pending tasks consulted");
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Operation(summary = "Get all completed tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found completed tasks",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tasks not found",
                    content = @Content) })
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        List<Task> tasks = taskService.getAllCompletedTasks();
        logger.info("All completed tasks consulted");
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
