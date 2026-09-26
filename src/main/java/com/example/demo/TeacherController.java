package com.example.demo;

import com.example.demo.dto.TeacherRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public Teacher createTeacher(
            @RequestBody @Valid TeacherRequest request) {

        return teacherService.createTeacher(
                request.getName(),
                request.getEmail()
        );
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(
            @PathVariable Long id,
            @RequestBody @Valid TeacherRequest request) {

        return teacherService.updateTeacher(
                id,
                request.getName(),
                request.getEmail()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }
}