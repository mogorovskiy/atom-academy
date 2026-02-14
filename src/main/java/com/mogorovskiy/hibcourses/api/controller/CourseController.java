package com.mogorovskiy.hibcourses.api.controller;

import com.mogorovskiy.hibcourses.api.CourseCreateRequest;
import com.mogorovskiy.hibcourses.domain.dto.AuthorDto;
import com.mogorovskiy.hibcourses.domain.dto.CourseDto;
import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;
import com.mogorovskiy.hibcourses.domain.mapper.CourseMapper;
import com.mogorovskiy.hibcourses.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseCreateRequest createRequest) {
        log.info("Creating course: {}", createRequest);

        CourseEntity entity = courseService.createCourse(createRequest);
        CourseDto courseDto = courseMapper.toCourseDto(entity);  //TODO: why author id = null in postman?

        return ResponseEntity.status(HttpStatus.CREATED).body(courseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable Long id) {
        log.info("Getting author by id: {}", id);

        CourseEntity entity = courseService.getCourse(id);
        CourseDto courseDto = courseMapper.toCourseDto(entity);

        return ResponseEntity.status(HttpStatus.OK).body(courseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDto> deleteCourse(@PathVariable Long id) {
        log.info("Deleting product by id: {}", id);

        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
