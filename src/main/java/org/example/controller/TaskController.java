package org.example.controller;


import org.example.model.Task;
import org.example.model.User;
import org.example.service.TaskBuilder;
import org.example.service.TaskService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    private final BeanFactory beanFactory;

    public TaskController(TaskService taskService, BeanFactory beanFactory) {
        this.taskService = taskService;
        this.beanFactory = beanFactory;
    }
    
    // GET /api/tasks - Get all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    
    // GET /api/tasks/{id} - Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // POST /api/tasks - Create new task
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Map<String,Object> taskData) {
        try {
            TaskBuilder taskBuilder =beanFactory.getBean(TaskBuilder.class);

            if(taskData.containsKey("title"))
            {
                taskBuilder.Withtile((String) taskData.get("title")) ;
            }
            if(taskData.containsKey("User"))
            {
                Object user = taskData.get("User");

                User ab =null;
                if(user instanceof User)
                {
                    ab   = (User) user;
                }

                taskBuilder.withUser(ab);
            }
            Task createdTask = taskService.createTask(taskBuilder.build());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        }
        catch(IllegalArgumentException ex)
        {
            return ResponseEntity.badRequest().body("Error creating task: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating task: " + e.getMessage());
        }
    }
    
    // PUT /api/tasks/{id} - Update task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> existingTask = taskService.getTaskById(id);
        if (existingTask.isPresent()) {
            task.setId(id);
            Task updatedTask = taskService.updateTask(task);
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }
    
    // DELETE /api/tasks/{id} - Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Optional<Task> existingTask = taskService.getTaskById(id);
        if (existingTask.isPresent()) {

        }
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
