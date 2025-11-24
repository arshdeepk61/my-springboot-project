package org.example.service;


import org.example.DTO.ProfileDto;
import org.example.model.Profile;
import org.example.model.User;
import org.example.repository.ProfileRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ProfileDto createOrUpdateProfile(Long userId, ProfileDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Profile profile = user.getProfile();
        if(profile == null) {
            profile = new Profile();
        }

        profile.setPhoneNumber(dto.getPhoneNumber());
        profile.setAddress(dto.getAddress());

        user.setProfile(profile);
        userRepository.save(user);

        dto.setId(profile.getId());
        dto.setUserId(user.getId());
        return dto;
    }

    @Transactional(readOnly = true)
    public ProfileDto getProfileByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Profile profile = user.getProfile();
        if(profile == null) return null;

        ProfileDto dto = new ProfileDto();
        dto.setId(profile.getId());
        dto.setPhoneNumber(profile.getPhoneNumber());
        dto.setAddress(profile.getAddress());
        dto.setUserId(user.getId());
        return dto;
    }
}
