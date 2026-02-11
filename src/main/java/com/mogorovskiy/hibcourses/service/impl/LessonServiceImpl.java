package com.mogorovskiy.hibcourses.service.impl;

import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;
import com.mogorovskiy.hibcourses.domain.entities.LessonEntity;
import com.mogorovskiy.hibcourses.repository.LessonRepository;
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

    @Transactional
    @Override
    public LessonEntity createLesson(LessonEntity lesson) {
        log.info("Creating author in DB: {}", lesson.getTitle());
        return lessonRepository.save(lesson);
    }

    @Override
    public LessonEntity updateLesson(LessonEntity lesson) {
        return null;
    }

    @Override
    public LessonEntity getLesson(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
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
