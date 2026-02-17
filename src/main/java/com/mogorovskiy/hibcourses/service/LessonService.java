package com.mogorovskiy.hibcourses.service;

import com.mogorovskiy.hibcourses.api.LessonCreateRequest;
import com.mogorovskiy.hibcourses.domain.entities.LessonEntity;

import java.util.List;

public interface LessonService {

    LessonEntity createLesson(LessonCreateRequest lessonCreateRequest);

    LessonEntity updateLesson(LessonEntity lesson);

    LessonEntity getLesson(Long id);

    List<LessonEntity> getAllLessons();

    void deleteLesson(Long id);
}
