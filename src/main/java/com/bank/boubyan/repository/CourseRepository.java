package com.bank.boubyan.repository;

import com.bank.boubyan.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> getCoursesByStudentsId(Integer studentId);
}
