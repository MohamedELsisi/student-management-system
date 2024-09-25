package com.bank.boubyan.service;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.RegistrationDTO;


import java.util.List;


public interface CourseService {
    List<CourseDTO> getAllCourses();

    List<CourseDTO> getStudentCourses();

    void registerCourse(RegistrationDTO registrationDTO) ;

    void cancelCourse(RegistrationDTO registrationDTO);
}
