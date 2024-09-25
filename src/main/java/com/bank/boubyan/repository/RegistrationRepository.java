package com.bank.boubyan.repository;

import com.bank.boubyan.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<StudentCourse, Integer> {
    Optional<StudentCourse> findByCourse_IdAndStudent_Id(Integer studentId, Integer courseId);
}
