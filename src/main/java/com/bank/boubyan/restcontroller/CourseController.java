package com.bank.boubyan.restcontroller;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.RegistrationDTO;
import com.bank.boubyan.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course Management", description = "APIs for managing student course registration, cancellation, and schedules")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);


    private CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @Operation(summary = "Get all available courses", description = "Returns a list of all courses available for registration.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of courses", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseDTO.class)))
    @GetMapping
    ResponseEntity<List<CourseDTO>> getAllCourses() {
        logger.info("Fetching all courses controller consumed");
        return ResponseEntity.ok(courseService.getAllCourses());
    }




    @Operation(summary = "Register for a course", description = "Allows a student to register for a course by providing course and student details.")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Course registration accepted"),
            @ApiResponse(responseCode = "400", description = "Invalid registration details")
    })
    @PostMapping("/register")
    ResponseEntity registerCourse(@RequestBody RegistrationDTO registrationDTO) {
        logger.info("Attempting to register course for student: {}", registrationDTO.getUserId());

        courseService.registerCourse(registrationDTO);
        return ResponseEntity.accepted().build();
    }



    @Operation(summary = "Cancel a course registration", description = "Allows a student to cancel an existing course registration.")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Course cancellation accepted"),
            @ApiResponse(responseCode = "400", description = "Invalid cancellation details")
    })
    @PostMapping("/cancel")
    ResponseEntity cancelCourse(@RequestBody RegistrationDTO registrationDTO) {
        courseService.cancelCourse(registrationDTO);
        return ResponseEntity.accepted().build();
    }




    @Operation(summary = "Download course schedule as PDF", description = "Downloads the course schedule for a student in PDF format.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "PDF schedule successfully downloaded", content = @Content(mediaType = "application/pdf")),
            @ApiResponse(responseCode = "404", description = "Student or schedule not found")
    })
    @GetMapping("/schedule/download")
    public ResponseEntity<byte[]> downloadCourseSchedulePdf(@RequestParam String username) {
        logger.info("Attempting to cancel course for student: {}", username);

        ByteArrayInputStream bis = courseService.downloadCourseSchedulePdf(username);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=course_schedule.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }
}
