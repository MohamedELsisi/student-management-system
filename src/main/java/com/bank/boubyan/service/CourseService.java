package com.bank.boubyan.service;

import com.bank.boubyan.dto.CourseDTO;


import java.util.List;


public interface CourseService {
    List<CourseDTO> getAllCourses();

    List<CourseDTO> getStudentCourses();


}
