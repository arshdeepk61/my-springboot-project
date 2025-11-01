package org.example.service;

import org.example.model.Task;
import org.example.model.TaskPriority;
import org.example.model.User;
import org.example.repository.TaskRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    
    @Override
    public Task createTask(Task task) {

        Task task1 = new TaskBuilder().withTaskPriority(TaskPriority.HIGH).Withtile("32").build();
        // If task has a user, make sure it's a managed entity from database
        if (task.getUser() != null && task.getUser().getId() != null) {
            Optional<User> user = userRepository.findById(task.getUser().getId());
            if (user.isPresent()) {
                task.setUser(user.get());
            } else {
                // User not found - set user to null to allow task creation without user
                task.setUser(null);
            }
        }
        return taskRepository.save(task);
    }
    
    @Override
    public Task updateTask(Task task) {
        // If task has a user, make sure it's a managed entity from database


        if (task.getUser() != null && task.getUser().getId() != null) {
            Optional<User> user = userRepository.findById(task.getUser().getId());
            if (user.isPresent()) {
                task.setUser(user.get());
            } else {
                // User not found - set user to null
                task.setUser(null);
            }
        }
        return taskRepository.save(task);
    }
    
    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}

