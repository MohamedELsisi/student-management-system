package com.bank.boubyan.service;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.RegistrationDTO;
import com.bank.boubyan.model.Course;


import java.io.ByteArrayInputStream;
import java.util.List;


public interface CourseService {
    List<CourseDTO> getAllCourses();

    List<CourseDTO> getStudentCourses();

    void registerCourse(RegistrationDTO registrationDTO);

    void cancelCourse(RegistrationDTO registrationDTO);
    ByteArrayInputStream downloadCourseSchedulePdf(String studentUserName);
}
