package org.example.service;

import org.example.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    
    List<Task> getAllTasks();
    
    Optional<Task> getTaskById(Long id);
    
    Task createTask(Task task);
    
    Task updateTask(Task task);
    
    void deleteTask(Long id);
}

