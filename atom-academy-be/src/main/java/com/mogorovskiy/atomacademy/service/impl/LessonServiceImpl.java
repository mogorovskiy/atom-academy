package com.mogorovskiy.atomacademy.service.impl;

import com.mogorovskiy.atomacademy.api.request.create.LessonCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.entities.CourseEntity;
import com.mogorovskiy.atomacademy.domain.entities.LessonEntity;
import com.mogorovskiy.atomacademy.repository.LessonRepository;
import com.mogorovskiy.atomacademy.service.CourseService;
import com.mogorovskiy.atomacademy.service.LessonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseService courseService;

    @Transactional
    @Override
    public LessonEntity createLesson(Long courseId, LessonCreateAndUpdateRequest createRequest) {
        log.info("Creating lesson in DB: {}", createRequest.title());
        CourseEntity courseEntity = courseService.getCourse(courseId);

        LessonEntity lessonEntity = LessonEntity.builder()
                .title(createRequest.title())
                .content(createRequest.content())
                .course(courseEntity)
                .build();
        return lessonRepository.save(lessonEntity);
    }

    @Transactional
    public LessonEntity updateLesson(Long id, LessonCreateAndUpdateRequest request) {
        LessonEntity entity = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));

        entity.setTitle(request.title());
        entity.setContent(request.content());

        return lessonRepository.save(entity);
    }

    @Transactional
    public LessonEntity patchLesson(Long id, LessonCreateAndUpdateRequest request) {
        LessonEntity entity = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));

        boolean changed = false;

        if (request.title() != null) {
            changed |= !request.title().equals(entity.getTitle());
            entity.setTitle(request.title());
        }
        if (request.content() != null) {
            changed |= !request.content().equals(entity.getContent());
            entity.setContent(request.content());
        }

        return changed ? lessonRepository.save(entity) : entity;
    }

    @Override
    public LessonEntity getLesson(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    @Override
    public List<LessonEntity> getLessonsByCourseId(Long courseId) {
        return lessonRepository.findLessonsByCourseId(courseId);
    }

    @Transactional
    @Override
    public void deleteLesson(Long id) {
        log.info("Deleting lesson by id: {}", id);
        lessonRepository.deleteById(id);
    }
}
