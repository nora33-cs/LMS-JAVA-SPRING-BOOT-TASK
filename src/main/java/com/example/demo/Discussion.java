package com.example.demo;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @JsonBackReference("student-discussions")
@ManyToOne
@JoinColumn(name = "student_id")
private Student student;

@JsonBackReference("course-discussions")
@ManyToOne
@JoinColumn(name = "course_id")
private Course course;

    public Discussion() {
    }

    public Discussion(String title, String content, Student student, Course course) {
        this.title = title;
        this.content = content;
        this.student = student;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

