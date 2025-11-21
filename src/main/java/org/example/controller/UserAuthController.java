package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserAuthController {

    private final UserService userService;

    @GetMapping("/sign")
    public ResponseEntity<Map<String,Object>> getSignIn() {
        Map<String, Object> response = new HashMap<>();
        response.put("Message", "public endpoint");
        return ResponseEntity.ok(response);
    }
    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

//    // GET /api/users - Get all users
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    // GET /api/users/{id} - Get user by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        Optional<User> user = userService.getUserById(id);
//        return user.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // POST /api/users - Create new user
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User createdUser = userService.createUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//    }
//
//    // PUT /api/users/{id} - Update user
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//        Optional<User> existingUser = userService.getUserById(id);
//        if (existingUser.isPresent()) {
//            user.setId(id);
//            User updatedUser = userService.updateUser(user);
//            return ResponseEntity.ok(updatedUser);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    // DELETE /api/users/{id} - Delete user
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        Optional<User> existingUser = userService.getUserById(id);
//        if (existingUser.isPresent()) {
//            userService.deleteUser(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}
