package com.example.demo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Course name is required")
    private String name;

    private String description;

    @JsonBackReference("teacher-courses")
    @ManyToOne

   @JoinColumn(name = "teacher_id")
private Teacher teacher;

@JsonManagedReference("course-enrollments")
@OneToMany(mappedBy = "course")
private List<Enrollment> enrollments;

@JsonManagedReference("course-discussions")
@OneToMany(mappedBy = "course")
private List<Discussion> discussions;


    public Course() {
    }

    public Course(String name, String description, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}