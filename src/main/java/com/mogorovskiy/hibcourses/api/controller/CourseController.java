package com.mogorovskiy.hibcourses.api.controller;

import com.mogorovskiy.hibcourses.api.request.create.CourseCreateRequest;
import com.mogorovskiy.hibcourses.api.request.update.CourseUpdateRequest;
import com.mogorovskiy.hibcourses.domain.dto.CourseDto;
import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;
import com.mogorovskiy.hibcourses.domain.mapper.CourseMapper;
import com.mogorovskiy.hibcourses.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
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

        CourseEntity entity = courseService.getCourse(id);
        CourseDto courseDto = courseMapper.toCourseDto(entity);

        return ResponseEntity.status(HttpStatus.OK).body(courseDto);
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        log.info("Getting all courses");
        List<CourseEntity> entities = courseService.getAllCourses();

        List<CourseDto> courseDtos = entities.stream()
                .map(courseMapper::toCourseDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(courseDtos);
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
