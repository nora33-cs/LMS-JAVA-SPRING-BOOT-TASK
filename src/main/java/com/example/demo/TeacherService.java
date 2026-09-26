package com.example.demo;

import com.example.demo.dto.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public Teacher createTeacher(String name, String email) {

        Teacher teacher = new Teacher();

        teacher.setName(name);
        teacher.setEmail(email);

        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {

        return teacherRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher with id " + id + " not found"
                        ));
    }

    @Transactional
    public Teacher updateTeacher(
            Long id,
            String name,
            String email) {

        Teacher teacher =
                teacherRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Teacher with id " + id + " not found"
                                ));

        teacher.setName(name);
        teacher.setEmail(email);

        return teacherRepository.save(teacher);
    }

    @Transactional
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}