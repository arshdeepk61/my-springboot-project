package org.example.controller;

import org.example.exception.ResourceNotFoundException;
import org.example.model.Task;
import org.example.service.TaskBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/scopes")
@CrossOrigin(origins = "*")
public class ScopeDemoController {
    // private final is same as autowiring
    // this is construtor injection , immutable , thread safe , no need to refection
    // @autowire is field is injection
    //@ contrcutor helps with circular dependecy
    // @ filed dont help with circular dependecy

    private final BeanFactory beanFactory;

    private final TaskBuilder taskBuilder1;

    private final TaskBuilder taskBuilder2;

    public ScopeDemoController(BeanFactory beanFactory, TaskBuilder taskBuilder1, TaskBuilder taskBuilder2) {
        this.beanFactory = beanFactory;
        this.taskBuilder1 = taskBuilder1;
        this.taskBuilder2 = taskBuilder2;
    }

    @GetMapping("/prototype")
    public ResponseEntity<Map<String,Object>> getScope() {
        Map<String,Object> response = new HashMap<>();
        response.put("Instance1",taskBuilder1.hashCode());
        response.put("Instance1.State",taskBuilder1.getCurrentstate());
        response.put("Instance2",taskBuilder2.hashCode());
        response.put("Instance2State",taskBuilder2.getCurrentstate());

        response.put("areSameInstace",taskBuilder1==taskBuilder2);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/throwNotexcpetion")
    public ResponseEntity<?> throwNotexcpetion(@RequestParam(defaultValue="task") String Resource, @RequestParam(defaultValue = "999") String ID) {

        throw new ResourceNotFoundException(Resource,ID);

       }
}
