package com.mogorovskiy.atomacademy.service;

import com.mogorovskiy.atomacademy.api.request.create.QuestionCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.dto.QuestionDto;
import com.mogorovskiy.atomacademy.domain.entities.QuestionEntity;

import java.util.List;

public interface QuestionService {

    QuestionDto createQuestion(Long courseId, QuestionCreateAndUpdateRequest QuestionCreateRequest);

    QuestionEntity updateQuestion(Long id, QuestionCreateAndUpdateRequest request);

    QuestionEntity patchQuestion(Long id, QuestionCreateAndUpdateRequest request);

    QuestionDto getQuestion(Long id);

    List<QuestionDto> getQuestionsByCourseId(Long courseId);

    void deleteQuestion(Long id);
}
