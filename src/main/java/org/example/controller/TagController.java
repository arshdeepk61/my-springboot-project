package org.example.controller;

import org.example.DTO.TagDto;
import org.example.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<TagDto> createTag(@RequestBody TagDto dto) {
        return ResponseEntity.ok(tagService.createTag(dto));
    }

    @PostMapping("/task/{taskId}/tag/{tagId}")
    public ResponseEntity<Void> addTagToTask(@PathVariable Long taskId,
                                             @PathVariable Long tagId) {
        tagService.addTagToTask(taskId, tagId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }
}
