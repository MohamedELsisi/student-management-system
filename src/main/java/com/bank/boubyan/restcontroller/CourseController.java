package com.bank.boubyan.restcontroller;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.RegistrationDTO;
import com.bank.boubyan.service.CourseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping("/register")
    ResponseEntity registerCourse(@RequestBody RegistrationDTO registrationDTO) {
        courseService.registerCourse(registrationDTO);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/cancel")
    ResponseEntity cancelCourse(@RequestBody RegistrationDTO registrationDTO) {
        courseService.cancelCourse(registrationDTO);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/schedule/download")
    public ResponseEntity<byte[]> downloadCourseSchedulePdf(@RequestParam String username) {
        ByteArrayInputStream bis = courseService.downloadCourseSchedulePdf(username);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=course_schedule.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }
}
