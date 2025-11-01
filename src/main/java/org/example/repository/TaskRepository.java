package org.example.repository;

import org.example.model.Task;
import org.example.model.TaskStatus;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // Find tasks by user
    List<Task> findByUser(User user);
    
    // Find tasks by status
    List<Task> findByStatus(TaskStatus status);
    
    // Find tasks by user and status
    List<Task> findByUserAndStatus(User user, TaskStatus status);
    
    // Find tasks by user ID
    @Query("SELECT t FROM Task t WHERE t.user.id = :userId")
    List<Task> findByUserId(@Param("userId") Long userId);
    
    // Find tasks by title containing (case insensitive)
    List<Task> findByTitleContainingIgnoreCase(String title);
    
    // Count tasks by status
    long countByStatus(TaskStatus status);
    
    // Find tasks by user ID and status
    @Query("SELECT t FROM Task t WHERE t.user.id = :userId AND t.status = :status")
    List<Task> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") TaskStatus status);
}
