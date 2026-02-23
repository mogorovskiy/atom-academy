package com.mogorovskiy.atomacademy.service;

import com.mogorovskiy.atomacademy.api.request.create.QuestionCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.entities.QuestionEntity;

import java.util.List;

public interface QuestionService {

    QuestionEntity createQuestion(Long courseId, QuestionCreateAndUpdateRequest QuestionCreateRequest);

    QuestionEntity updateQuestion(Long id, QuestionCreateAndUpdateRequest request);

    QuestionEntity patchQuestion(Long id, QuestionCreateAndUpdateRequest request);

    QuestionEntity getQuestion(Long id);

    List<QuestionEntity> getQuestionsByCourseId(Long courseId);

    void deleteQuestion(Long id);
}
