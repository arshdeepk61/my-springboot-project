package org.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SecuirtyDemoController {

    @GetMapping("/public/info")
    public ResponseEntity<Map<String,Object>> getPublicInfo() {
        Map<String,Object> response = new HashMap<>();
        response.put("Message","public endpoint");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/authicated/profile")
    public ResponseEntity<Map<String,Object>> getProfile() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Map<String,Object> profile = new HashMap<>();
        profile.put("username",authentication.getName());
        profile.put("authorities",authentication.getAuthorities());
        profile.put("Authenticated",authentication.isAuthenticated());
        profile.put("Message","you are authenticated");
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/public/oautherror")
    public ResponseEntity<Map<String,Object>> getPublicError() {
        Map<String,Object> response = new HashMap<>();
        response.put("Message","auth 2 login failure");
        response.put("Status","Error");
        return ResponseEntity.badRequest().body(response);
    }
}
