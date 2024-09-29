package com.bank.boubyan.util;

import com.bank.boubyan.model.Course;
import com.bank.boubyan.model.Student;
import com.bank.boubyan.repository.CourseRepository;
import com.bank.boubyan.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        System.out.println();
        System.out.println("------------------------------------");
        Course course1 = new Course();

        course1.setName("Introduction to Programming");
        course1.setDescription("Learn the basics of programming.");
        course1.setRating(5);
        course1.setInstructorName("John Doe");
        course1.setStartDate(LocalDateTime.of(2024, 1, 15, 9, 0));

        Course course2 = new Course();
        course2.setName("Advanced Java");
        course2.setDescription("Deep dive into Java programming.");
        course2.setRating(4);
        course2.setInstructorName("Jane Smith");
        course2.setStartDate(LocalDateTime.of(2024, 2, 20, 10, 0));

        Course course3 = new Course();
        course3.setId(3);
        course3.setName("Data Structures");
        course3.setDescription("Understanding data structures in computer science.");
        course3.setRating(5);
        course3.setInstructorName("Alice Johnson");
        course3.setStartDate(LocalDateTime.of(2024, 3, 10, 11, 0));

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);


        Student student1 = new Student();
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setEmail("john.doe@example.com");
        student1.setUsername("MELSISI");
        student1.setPassword(passwordEncoder.encode("123456"));
        student1.setDateOfBirth(LocalDateTime.of(2000, 6, 15, 0, 0));
        student1.setAuthorities("USER");


        studentRepository.save(student1);

    }
}
