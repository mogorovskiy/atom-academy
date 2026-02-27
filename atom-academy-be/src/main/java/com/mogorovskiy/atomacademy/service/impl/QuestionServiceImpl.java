package com.mogorovskiy.atomacademy.service.impl;

import com.mogorovskiy.atomacademy.api.request.create.QuestionCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.CacheNames;
import com.mogorovskiy.atomacademy.domain.dto.QuestionDto;
import com.mogorovskiy.atomacademy.domain.entities.CourseEntity;
import com.mogorovskiy.atomacademy.domain.entities.QuestionEntity;
import com.mogorovskiy.atomacademy.domain.mapper.QuestionMapper;
import com.mogorovskiy.atomacademy.repository.QuestionRepository;
import com.mogorovskiy.atomacademy.service.CourseService;
import com.mogorovskiy.atomacademy.service.QuestionService;
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
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final CourseService courseService;

    @Transactional
    @Override
    @CacheEvict(value = CacheNames.QUESTION, key = "#courseId")
    public QuestionDto createQuestion(Long courseId, QuestionCreateAndUpdateRequest createRequest) {
        log.info("Creating question for course {}: {}", courseId, createRequest.question());
        CourseEntity courseEntity = courseService.getCourseEntity(courseId);

        QuestionEntity questionEntity = QuestionEntity.builder()
                .question(createRequest.question())
                .answer(createRequest.answer())
                .course(courseEntity)
                .build();

        QuestionEntity saved = questionRepository.save(questionEntity);
        return questionMapper.toQuestionDto(saved);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.QUESTION_DETAILS, key = "#id"),
            @CacheEvict(value = CacheNames.QUESTION, allEntries = true)
    })
    public QuestionEntity updateQuestion(Long id, QuestionCreateAndUpdateRequest request) {
        QuestionEntity entity = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        entity.setQuestion(request.question());
        entity.setAnswer(request.answer());

        return questionRepository.save(entity);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.QUESTION_DETAILS, key = "#id"),
            @CacheEvict(value = CacheNames.QUESTION, allEntries = true)
    })
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

    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = CacheNames.QUESTION_DETAILS, key = "#id")
    public QuestionDto getQuestion(Long id) {
        log.info("Fetching question details from DB: {}", id);
        return questionRepository.findById(id)
                .map(questionMapper::toQuestionDto)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = CacheNames.QUESTION, key = "#courseId")
    public List<QuestionDto> getQuestionsByCourseId(Long courseId) {
        log.info("Fetching questions from DB for course: {}", courseId);
        return questionRepository.findQuestionsByCourseId(courseId).stream()
                .map(questionMapper::toQuestionDto)
                .toList();
    }

    @Transactional
    @Override
    @Caching(evict = {
            @CacheEvict(value = CacheNames.QUESTION_DETAILS, key = "#id"),
            @CacheEvict(value = CacheNames.QUESTION, allEntries = true)
    })
    public void deleteQuestion(Long id) {
        log.info("Deleting question id: {}", id);
        questionRepository.deleteById(id);
    }
}
