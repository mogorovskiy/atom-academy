package com.mogorovskiy.atomacademy.service.common;

import com.mogorovskiy.atomacademy.api.request.common.create.QuestionCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.dto.FullQuestionDto;
import com.mogorovskiy.atomacademy.domain.dto.ShortQuestionDto;
import com.mogorovskiy.atomacademy.domain.entities.QuestionEntity;

import java.util.List;

public interface QuestionService {

    FullQuestionDto createQuestion(Long courseId, QuestionCreateAndUpdateRequest QuestionCreateRequest);

    QuestionEntity updateQuestion(Long id, QuestionCreateAndUpdateRequest request);

    QuestionEntity patchQuestion(Long id, QuestionCreateAndUpdateRequest request);

    FullQuestionDto getQuestion(Long id);

    List<ShortQuestionDto> getQuestionsByCourseId(Long courseId);

    void deleteQuestion(Long id);
}
