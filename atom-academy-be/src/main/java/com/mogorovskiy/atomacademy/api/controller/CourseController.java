package com.mogorovskiy.atomacademy.api.controller;

import com.mogorovskiy.atomacademy.api.request.create.CourseCreateRequest;
import com.mogorovskiy.atomacademy.api.request.update.CourseUpdateRequest;
import com.mogorovskiy.atomacademy.domain.dto.CourseDto;
import com.mogorovskiy.atomacademy.domain.entities.CourseEntity;
import com.mogorovskiy.atomacademy.domain.mapper.CourseMapper;
import com.mogorovskiy.atomacademy.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseCreateRequest createRequest) {
        log.info("Creating course: {}", createRequest);

        CourseEntity entity = courseService.createCourse(createRequest);
        CourseDto courseDto = courseMapper.toCourseDto(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(courseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable Long id) {
        log.info("Getting course by id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourse(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        log.info("Getting all courses");
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseUpdateRequest updateRequest
    ) {
        log.info("Full update for course id {}: {}", id, updateRequest);
        CourseEntity entity = courseService.updateCourse(id, updateRequest);
        return ResponseEntity.ok(courseMapper.toCourseDto(entity));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseDto> patchCourse(
            @PathVariable Long id,
            @RequestBody CourseUpdateRequest updateRequest
    ) {
        log.info("Partial update (patch) for course id {}: {}", id, updateRequest);
        CourseEntity entity = courseService.patchCourse(id, updateRequest);
        return ResponseEntity.ok(courseMapper.toCourseDto(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDto> deleteCourse(@PathVariable Long id) {
        log.info("Deleting course by id: {}", id);

        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
