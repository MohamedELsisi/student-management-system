package com.bank.boubyan.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "student_course")
public class StudentCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentCourseGen")
    @SequenceGenerator(name = "studentCourseGen", sequenceName = "STUDENT_COURSE_SEQ", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
