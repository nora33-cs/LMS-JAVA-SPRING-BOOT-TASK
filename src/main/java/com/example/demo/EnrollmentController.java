package com.example.demo;

import com.example.demo.dto.EnrollmentRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public Enrollment createEnrollment(
            @RequestBody @Valid EnrollmentRequest request) {

        return enrollmentService.createEnrollment(
                request.getStudentId(),
                request.getCourseId()
        );
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public Enrollment getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @PutMapping("/{id}")
    public Enrollment updateEnrollment(
            @PathVariable Long id,
            @RequestBody @Valid EnrollmentRequest request) {

        return enrollmentService.updateEnrollment(
                id,
                request.getStudentId(),
                request.getCourseId()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
    }
}