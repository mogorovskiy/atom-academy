package com.mogorovskiy.atomacademy.service.impl;

import com.mogorovskiy.atomacademy.api.request.create.QuestionCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.entities.CourseEntity;
import com.mogorovskiy.atomacademy.domain.entities.QuestionEntity;
import com.mogorovskiy.atomacademy.repository.QuestionRepository;
import com.mogorovskiy.atomacademy.service.CourseService;
import com.mogorovskiy.atomacademy.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final CourseService courseService;

    @Transactional
    @Override
    public QuestionEntity createQuestion(Long courseId, QuestionCreateAndUpdateRequest createRequest) {
        log.info("Creating question in DB: {}", createRequest.question());
        CourseEntity courseEntity = courseService.getCourse(courseId);

        QuestionEntity questionEntity = QuestionEntity.builder()
                .question(createRequest.question())
                .answer(createRequest.answer())
                .course(courseEntity)
                .build();
        return questionRepository.save(questionEntity);
    }

    @Transactional
    public QuestionEntity updateQuestion(Long id, QuestionCreateAndUpdateRequest request) {
        QuestionEntity entity = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        entity.setQuestion(request.question());
        entity.setAnswer(request.answer());

        return questionRepository.save(entity);
    }

    @Transactional
    public QuestionEntity patchQuestion(Long id, QuestionCreateAndUpdateRequest request) {
        QuestionEntity entity = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        boolean changed = false;

        if (request.question() != null) {
            changed |= !request.question().equals(entity.getQuestion());
            entity.setQuestion(request.question());
        }
        if (request.answer() != null) {
            changed |= !request.answer().equals(entity.getAnswer());
            entity.setAnswer(request.answer());
        }

        return changed ? questionRepository.save(entity) : entity;
    }

    @Override
    public QuestionEntity getQuestion(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    @Override
    public List<QuestionEntity> getQuestionsByCourseId(Long courseId) {
        return questionRepository.findQuestionsByCourseId(courseId);
    }

    @Transactional
    @Override
    public void deleteQuestion(Long id) {
        log.info("Deleting question by id: {}", id);
        questionRepository.deleteById(id);
    }
}
