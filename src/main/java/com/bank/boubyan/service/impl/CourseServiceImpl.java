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
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfWriter;

@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);


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
        logger.info("Fetching all courses");
        List<Course> courses = courseRepository.findAll();
        return courseMapper.courseDTOList(courses);
    }

    @Override
    @Transactional
    public void registerCourse(RegistrationDTO registrationDTO) {
        logger.info("Registering course for student ID: {}", registrationDTO.getUserId());

        Student student = studentRepository.findById(registrationDTO.getUserId()).orElseThrow(() -> {
            logger.error("Student not found with ID: {}", registrationDTO.getUserId());
            return new RuntimeException("Student not found");
        });

        Course course = courseRepository.findById(registrationDTO.getCourseId()).orElseThrow(() -> {
            logger.error("Course not found with ID: {}", registrationDTO.getCourseId());
            return new RuntimeException("Course not found");
        });

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        registrationRepository.save(studentCourse);

        logger.info("Successfully registered student {} for course {}", student.getUsername(), course.getName());
    }

    @Override
    @Transactional
    public void cancelCourse(RegistrationDTO registrationDTO) {
        logger.info("Cancelling course registration for student ID: {}", registrationDTO.getUserId());

        StudentCourse studentCourse = registrationRepository.findByCourse_IdAndStudent_Id(registrationDTO.getCourseId(), registrationDTO.getUserId()).orElseThrow(() -> {
            logger.error("Registration not found for student ID: {} and course ID: {}", registrationDTO.getUserId(), registrationDTO.getCourseId());
            return new RuntimeException("Registration not found");
        });

        registrationRepository.delete(studentCourse);

        logger.info("Successfully cancelled course registration for student ID: {}", registrationDTO.getUserId());
    }

    @Override
    public ByteArrayInputStream downloadCourseSchedulePdf(String studentUserName) {
        logger.info("Downloading course schedule PDF for student: {}", studentUserName);

        Student student = studentRepository.findByUsername(studentUserName).orElseThrow(() -> {
            logger.error("Student not found with username: {}", studentUserName);
            return new RuntimeException("Student not found");
        });

        List<Course> courses = courseRepository.getCoursesByStudentsId(student.getId());
        return generateCourseSchedulePdf(studentUserName, courses);
    }


    private ByteArrayInputStream generateCourseSchedulePdf(String studentName, List<Course> courses) {
        logger.info("Generating course schedule PDF for student: {}", studentName);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);

            com.itextpdf.kernel.pdf.PdfDocument pdf = new com.itextpdf.kernel.pdf.PdfDocument(writer);
            Document document = new Document(pdf);
            document.add(new Paragraph("Course Schedule for " + studentName));


            for (Course course : courses) {
                document.add(new Paragraph("Course: " + course.getName() + " | Date: " + course.getDescription() + " | Instructor: " + course.getInstructorName()));
            }
            document.close();
        } catch (Exception e) {
            logger.error("Error generating course schedule PDF", e);
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

}

