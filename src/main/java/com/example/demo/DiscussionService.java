package com.example.demo;

import com.example.demo.dto.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiscussionService {

    private final DiscussionRepository discussionRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public DiscussionService(
            DiscussionRepository discussionRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.discussionRepository = discussionRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class
    )
    public Discussion createDiscussion(
            String title,
            String content,
            Long studentId,
            Long courseId) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student with id " + studentId + " not found"
                                ));

        Course course =
                courseRepository.findById(courseId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Course with id " + courseId + " not found"
                                ));

        Discussion discussion =
                new Discussion(
                        title,
                        content,
                        student,
                        course
                );

        return discussionRepository.save(discussion);
    }

    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }

    public Discussion getDiscussionById(Long id) {

        return discussionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Discussion with id " + id + " not found"
                        ));
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class
    )
    public Discussion updateDiscussion(
            Long id,
            String title,
            String content,
            Long studentId,
            Long courseId) {

        Discussion discussion =
                discussionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Discussion with id " + id + " not found"
                                ));

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student with id " + studentId + " not found"
                                ));

        Course course =
                courseRepository.findById(courseId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Course with id " + courseId + " not found"
                                ));

        discussion.setTitle(title);
        discussion.setContent(content);
        discussion.setStudent(student);
        discussion.setCourse(course);

        return discussionRepository.save(discussion);
    }

    @Transactional
    public void deleteDiscussion(Long id) {
        discussionRepository.deleteById(id);
    }

    public List<Discussion> getDiscussionsByCourse(Long courseId) {

        courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with id " + courseId + " not found"
                        ));

        return discussionRepository.findByCourseId(courseId);
    }
}