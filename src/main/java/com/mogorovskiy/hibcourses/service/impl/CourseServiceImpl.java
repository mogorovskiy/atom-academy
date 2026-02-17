package com.mogorovskiy.hibcourses.service.impl;

import com.mogorovskiy.hibcourses.api.CourseCreateRequest;
import com.mogorovskiy.hibcourses.domain.entities.AuthorEntity;
import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;
import com.mogorovskiy.hibcourses.repository.CourseRepository;
import com.mogorovskiy.hibcourses.service.AuthorService;
import com.mogorovskiy.hibcourses.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
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
    private final AuthorService authorService;

    @Transactional
    @Override
    public CourseEntity createCourse(CourseCreateRequest createRequest) {
        log.info("Creating course in DB: {}", createRequest.title());
        AuthorEntity author = authorService.getAuthor(createRequest.authorId());

        CourseEntity courseEntity = CourseEntity.builder()
                .author(author)
                .title(createRequest.title())
                .complexity(createRequest.complexity())
                .description(createRequest.description())
                .build();
        return courseRepository.save(courseEntity);
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
