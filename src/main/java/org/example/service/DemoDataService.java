package org.example.service;

import org.example.model.Task;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoDataService {

    private final UserRepository userRepository;

    public DemoDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createSampleUserWithTasks() {
        User user = new User("arsh", "arsh@example.com", "Arshdeep Kaur");

        Task t1 = new Task("Learn Spring Security", "MDC, filters, etc.", user);
        Task t2 = new Task("Practice Hibernate", "Relations, mappings", user);

        user.addTask(t1);
        user.addTask(t2);

        userRepository.save(user); // thanks to cascade, tasks will be saved too
    }
}
