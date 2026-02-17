package com.mogorovskiy.hibcourses.service.impl;

import com.mogorovskiy.hibcourses.api.LessonCreateRequest;
import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;
import com.mogorovskiy.hibcourses.domain.entities.LessonEntity;
import com.mogorovskiy.hibcourses.repository.LessonRepository;
import com.mogorovskiy.hibcourses.service.CourseService;
import com.mogorovskiy.hibcourses.service.LessonService;
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
    public LessonEntity createLesson(LessonCreateRequest createRequest) {
        log.info("Creating lesson in DB: {}", createRequest.title());
        CourseEntity courseEntity = courseService.getCourse(createRequest.courseId());

        LessonEntity lessonEntity = LessonEntity.builder()
                .title(createRequest.title())
                .content(createRequest.content())
                .course(courseEntity)
                .build();
        return lessonRepository.save(lessonEntity);
    }

    @Override
    public LessonEntity updateLesson(LessonEntity lesson) {
        return null;
    }

    @Override
    public LessonEntity getLesson(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    @Override
    public List<LessonEntity> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteLesson(Long id) {
        log.info("Deleting lesson by id: {}", id);
        lessonRepository.deleteById(id);
    }
}
