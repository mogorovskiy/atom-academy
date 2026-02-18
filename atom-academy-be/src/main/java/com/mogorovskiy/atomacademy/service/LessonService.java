package com.mogorovskiy.atomacademy.service;

import com.mogorovskiy.atomacademy.api.request.create.LessonCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.entities.LessonEntity;

import java.util.List;

public interface LessonService {

    LessonEntity createLesson(Long courseId, LessonCreateAndUpdateRequest lessonCreateRequest);

    LessonEntity updateLesson(Long id, LessonCreateAndUpdateRequest request);

    LessonEntity patchLesson(Long id, LessonCreateAndUpdateRequest request);

    LessonEntity getLesson(Long id);

    List<LessonEntity> getAllLessons();

    void deleteLesson(Long id);
}
