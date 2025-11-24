package org.example.service;



import org.example.DTO.TagDto;
import org.example.model.Tag;
import org.example.repository.TagRepository;
import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TaskRepository taskRepository;

    public TagService(TagRepository tagRepository, TaskRepository taskRepository) {
        this.tagRepository = tagRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TagDto createTag(TagDto dto) {
        Tag tag = new Tag();
        tag.setName(dto.getName());
        Tag saved = tagRepository.save(tag);
        dto.setId(saved.getId());
        return dto;
    }

    @Transactional
    public void addTagToTask(Long taskId, Long tagId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("Tag not found"));

        task.addTag(tag);
        taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<TagDto> getAllTags() {
        return tagRepository.findAll().stream()
                .map(t -> {
                    TagDto dto = new TagDto();
                    dto.setId(t.getId());
                    dto.setName(t.getName());
                    return dto;
                }).collect(Collectors.toList());
    }
}
