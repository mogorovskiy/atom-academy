package com.mogorovskiy.atomacademy.service.impl;

import com.mogorovskiy.atomacademy.api.request.create.CourseCreateRequest;
import com.mogorovskiy.atomacademy.api.request.update.CourseUpdateRequest;
import com.mogorovskiy.atomacademy.domain.CacheNames;
import com.mogorovskiy.atomacademy.domain.dto.CourseDto;
import com.mogorovskiy.atomacademy.domain.entities.UserEntity;
import com.mogorovskiy.atomacademy.domain.entities.CourseEntity;
import com.mogorovskiy.atomacademy.domain.mapper.CourseMapper;
import com.mogorovskiy.atomacademy.repository.CourseRepository;
import com.mogorovskiy.atomacademy.service.UserService;
import com.mogorovskiy.atomacademy.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final UserService userService;
    private final CourseRepository courseRepository;

    @Transactional
    @Override
    @CacheEvict(value = CacheNames.COURSES_LIST, allEntries = true)
    public CourseEntity createCourse(CourseCreateRequest createRequest) {
        log.info("Creating course in DB: {}", createRequest.title());
        UserEntity creator = userService.getUser(createRequest.creatorId());

        CourseEntity courseEntity = CourseEntity.builder()
                .creator(creator)
                .title(createRequest.title())
                .complexity(createRequest.complexity())
                .description(createRequest.description())
                .build();
        return courseRepository.save(courseEntity);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.COURSE, key = "#id"),
            @CacheEvict(value = CacheNames.COURSES_LIST, allEntries = true)
    })
    public CourseEntity updateCourse(Long id, CourseUpdateRequest updateRequest) {
        CourseEntity entity = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        entity.setTitle(updateRequest.title());
        entity.setDescription(updateRequest.description());
        entity.setComplexity(updateRequest.complexity());

        return courseRepository.save(entity);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.COURSE, key = "#id"),
            @CacheEvict(value = CacheNames.COURSES_LIST, allEntries = true)
    })
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
    @Transactional(readOnly = true)
    @Cacheable(value = CacheNames.COURSE, key = "#id")
    public CourseDto getCourse(Long id) {
        log.info("Fetching course from DB for id: {}", id);
        return courseRepository.findById(id)
                .map(courseMapper::toCourseDto)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CacheNames.COURSES_LIST)
    public List<CourseDto> getAllCourses() {
        log.info("Fetching all courses from DB");
        return courseRepository.findAllWithCreators().stream()
                .map(courseMapper::toCourseDto)
                .toList();
    }

    @Override
    public CourseEntity getCourseEntity(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }

    @Transactional
    @Override
    @Caching(evict = {
            @CacheEvict(value = CacheNames.COURSE, key = "#id"),
            @CacheEvict(value = CacheNames.COURSES_LIST, allEntries = true)
    })
    public void deleteCourse(Long id) {
        log.info("Deleting course by id: {}", id);
        courseRepository.deleteById(id);
    }
}
