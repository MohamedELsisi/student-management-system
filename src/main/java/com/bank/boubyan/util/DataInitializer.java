package com.bank.boubyan.util;

import com.bank.boubyan.model.Course;
import com.bank.boubyan.model.Student;
import com.bank.boubyan.repository.CourseRepository;
import com.bank.boubyan.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public void run(String... args) throws Exception {

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

        // Create and save students
        Student student1 = new Student();

        student1.setFirstName("Michael");
        student1.setLastName("Brown");
        student1.setEmail("michael.brown@example.com");
        student1.setDateOfBirth(LocalDateTime.of(2000, 5, 15, 0, 0));

        Student student2 = new Student();
        student2.setId(2);
        student2.setFirstName("Emily");
        student2.setLastName("Davis");
        student2.setEmail("emily.davis@example.com");
        student2.setDateOfBirth(LocalDateTime.of(1999, 8, 22, 0, 0));

        Student student3 = new Student();
        student3.setId(3);
        student3.setFirstName("Daniel");
        student3.setLastName("Wilson");
        student3.setEmail("daniel.wilson@example.com");
        student3.setDateOfBirth(LocalDateTime.of(2001, 12, 30, 0, 0));

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
    }
}
