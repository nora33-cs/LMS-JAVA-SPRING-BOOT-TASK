package com.example.demo;

import com.example.demo.dto.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(
            CourseRepository courseRepository,
            TeacherRepository teacherRepository) {

        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public Course createCourse(String name, Long teacherId) {

        Teacher teacher =
                teacherRepository.findById(teacherId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Teacher with id " + teacherId + " not found"
                                ));

        Course course = new Course();

        course.setName(name);
        course.setTeacher(teacher);

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {

        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with id " + id + " not found"
                        ));
    }

    @Transactional
    public Course updateCourse(
            Long id,
            String name,
            Long teacherId) {

        Course course =
                courseRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Course with id " + id + " not found"
                                ));

        Teacher teacher =
                teacherRepository.findById(teacherId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Teacher with id " + teacherId + " not found"
                                ));

        course.setName(name);
        course.setTeacher(teacher);

        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}