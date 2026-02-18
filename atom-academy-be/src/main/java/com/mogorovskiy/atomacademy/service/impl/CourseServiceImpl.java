package com.mogorovskiy.atomacademy.service.impl;

import com.mogorovskiy.atomacademy.api.request.create.CourseCreateRequest;
import com.mogorovskiy.atomacademy.api.request.update.CourseUpdateRequest;
import com.mogorovskiy.atomacademy.domain.entities.AuthorEntity;
import com.mogorovskiy.atomacademy.domain.entities.CourseEntity;
import com.mogorovskiy.atomacademy.repository.CourseRepository;
import com.mogorovskiy.atomacademy.service.AuthorService;
import com.mogorovskiy.atomacademy.service.CourseService;
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

    @Transactional
    public CourseEntity updateCourse(Long id, CourseUpdateRequest updateRequest) {
        CourseEntity entity = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        entity.setTitle(updateRequest.title());
        entity.setDescription(updateRequest.description());
        entity.setComplexity(updateRequest.complexity());

        return courseRepository.save(entity);
    }

    @Transactional
    public CourseEntity patchCourse(Long id, CourseUpdateRequest dto) {
        CourseEntity entity = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        boolean changed = false;

        if (dto.title() != null) {
            changed |= !dto.title().equals(entity.getTitle());
            entity.setTitle(dto.title());
        }
        if (dto.description() != null) {
            changed |= !dto.description().equals(entity.getDescription());
            entity.setDescription(dto.description());
        }
        if (dto.complexity() != null) {
            changed |= (entity.getComplexity() != dto.complexity());
            entity.setComplexity(dto.complexity());
        }

        return changed ? courseRepository.save(entity) : entity;
    }

    @Override
    public CourseEntity getCourse(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAllWithAuthors();
    }

    @Transactional
    @Override
    public void deleteCourse(Long id) {
        log.info("Deleting course by id: {}", id);
        courseRepository.deleteById(id);
    }
}
