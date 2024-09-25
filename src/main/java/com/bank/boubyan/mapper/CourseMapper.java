package com.bank.boubyan.mapper;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.model.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {

    public List<CourseDTO> courseDTOList(List<Course> courseList) {
        List<CourseDTO> courseDTOS = new ArrayList<>();
        courseList.forEach((c)-> {
            var courseDTO = getCourseDTO(c);
            courseDTOS.add(courseDTO);
        });
        return courseDTOS;
    }

    private CourseDTO getCourseDTO(Course course) {
        var courseDTO = new CourseDTO();
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setId(course.getId());
        courseDTO.setInstructorName(course.getInstructorName());
        courseDTO.setRate(course.getRating());
        return courseDTO;
    }
}
