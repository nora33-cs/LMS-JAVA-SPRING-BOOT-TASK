package com.example.demo;

import com.example.demo.dto.DiscussionRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discussions")
public class DiscussionController {

    private final DiscussionService discussionService;

    public DiscussionController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    @PostMapping
    public Discussion createDiscussion(
            @RequestBody @Valid DiscussionRequest request) {

        return discussionService.createDiscussion(
                request.getTitle(),
                request.getContent(),
                request.getStudentId(),
                request.getCourseId()
        );
    }

    @GetMapping
    public List<Discussion> getAllDiscussions() {
        return discussionService.getAllDiscussions();
    }

    @GetMapping("/{id}")
    public Discussion getDiscussionById(@PathVariable Long id) {
        return discussionService.getDiscussionById(id);
    }

    @PutMapping("/{id}")
    public Discussion updateDiscussion(
            @PathVariable Long id,
            @RequestBody @Valid DiscussionRequest request) {

        return discussionService.updateDiscussion(
                id,
                request.getTitle(),
                request.getContent(),
                request.getStudentId(),
                request.getCourseId()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteDiscussion(@PathVariable Long id) {
        discussionService.deleteDiscussion(id);
    }

    @GetMapping("/course/{courseId}")
    public List<Discussion> getDiscussionsByCourse(
            @PathVariable Long courseId) {

        return discussionService.getDiscussionsByCourse(courseId);
    }
}