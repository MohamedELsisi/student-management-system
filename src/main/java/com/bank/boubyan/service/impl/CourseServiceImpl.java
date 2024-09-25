package com.bank.boubyan.service.impl;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.RegistrationDTO;
import com.bank.boubyan.mapper.CourseMapper;
import com.bank.boubyan.model.Course;
import com.bank.boubyan.model.Student;
import com.bank.boubyan.model.StudentCourse;
import com.bank.boubyan.repository.CourseRepository;
import com.bank.boubyan.repository.RegistrationRepository;
import com.bank.boubyan.repository.StudentRepository;
import com.bank.boubyan.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private RegistrationRepository registrationRepository;
    private CourseMapper courseMapper;


    public CourseServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, RegistrationRepository registrationRepository, CourseMapper courseMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
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

    @Override
    @Transactional
    public void registerCourse(RegistrationDTO registrationDTO) {
        StudentCourse studentCourse = new StudentCourse();
        Student student = studentRepository.findById(registrationDTO.getUserId()).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(registrationDTO.getCourseId()).orElseThrow(() -> new RuntimeException("Course not found"));
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        registrationRepository.save(studentCourse);
    }

    @Override
    @Transactional
    public void cancelCourse(RegistrationDTO registrationDTO) {
        StudentCourse studentCourse = registrationRepository.findByCourse_IdAndStudent_Id(registrationDTO.getCourseId(), registrationDTO.getUserId()).orElseThrow(
                () -> new RuntimeException("Registration not found"));
        registrationRepository.delete(studentCourse);
    }

}

