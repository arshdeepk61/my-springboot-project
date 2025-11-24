package org.example.controller;

import org.example.DTO.TaskRequestDto;
import org.example.DTO.TaskResponseDto;
import org.example.service.TaskService;
import org.example.service.TaskService1;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController1 {
    private final TaskService1 taskService;

    public TaskController1(TaskService1 taskService) {
        this.taskService = taskService;
    }

    // Create task (optionally assign to user by userId in dto)
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto dto) {
        TaskResponseDto created = taskService.createTask(dto);
        return ResponseEntity.ok(created);
    }

    // Get task by id
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    // Get tasks for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskResponseDto>> getTasksForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksForUser(userId));
    }

    // Update task status
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponseDto> updateStatus(@PathVariable Long id,
                                                        @RequestParam String status) {
        TaskResponseDto updated = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    // Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
