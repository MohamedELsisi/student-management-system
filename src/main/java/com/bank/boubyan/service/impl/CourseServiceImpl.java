package com.bank.boubyan.service.impl;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.mapper.CourseMapper;
import com.bank.boubyan.model.Course;
import com.bank.boubyan.repository.CourseRepository;
import com.bank.boubyan.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private CourseMapper courseMapper;


    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.courseDTOList(courses);
    }

    @Override
    public List<CourseDTO> getStudentCourses() {
        return List.of();
    }
}
