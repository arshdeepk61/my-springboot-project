package org.example.controller;

import org.example.DTO.ProfileDto;
import org.example.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController1 {
    private final ProfileService profileService;

    public ProfileController1(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<ProfileDto> createOrUpdateProfile(
            @PathVariable Long userId,
            @RequestBody ProfileDto dto) {
        return ResponseEntity.ok(profileService.createOrUpdateProfile(userId, dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(profileService.getProfileByUserId(userId));
    }
}
