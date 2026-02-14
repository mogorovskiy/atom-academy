package com.mogorovskiy.hibcourses.service.impl;

import com.mogorovskiy.hibcourses.api.CourseCreateRequest;
import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;
import com.mogorovskiy.hibcourses.repository.CourseRepository;
import com.mogorovskiy.hibcourses.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Transactional
    @Override
    public CourseEntity createCourse(CourseCreateRequest course) {
        log.info("Creating author in DB: {}", course.title());

        CourseEntity courseEntity = CourseEntity.
        return courseRepository.save(course);
    }

    @Override
    public CourseEntity updateCourse(CourseCreateRequest course) {
        return null;
    }

    @Override
    public CourseEntity getCourse(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteCourse(Long id) {
        log.info("Deleting course by id: {}", id);
        courseRepository.deleteById(id);
    }
}
