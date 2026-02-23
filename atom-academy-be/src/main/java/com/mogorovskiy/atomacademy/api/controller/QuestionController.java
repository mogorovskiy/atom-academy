package com.mogorovskiy.atomacademy.api.controller;

import com.mogorovskiy.atomacademy.api.request.create.QuestionCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.dto.QuestionDto;
import com.mogorovskiy.atomacademy.domain.entities.QuestionEntity;
import com.mogorovskiy.atomacademy.domain.mapper.QuestionMapper;
import com.mogorovskiy.atomacademy.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @PostMapping("/courses/{courseId}/questions")
    public ResponseEntity<QuestionDto> createQuestion(
            @PathVariable Long courseId,
            @RequestBody QuestionCreateAndUpdateRequest createRequest
    ) {
        log.info("Creating question: {}", createRequest);

        QuestionEntity entity = questionService.createQuestion(courseId, createRequest);
        QuestionDto questionDto = questionMapper.toQuestionDto(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(questionDto);
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<QuestionDto> getById(@PathVariable Long id) {
        log.info("Getting question by id: {}", id);

        QuestionEntity entity = questionService.getQuestion(id);
        QuestionDto questionDto = questionMapper.toQuestionDto(entity);

        return ResponseEntity.status(HttpStatus.OK).body(questionDto);
    }

    @GetMapping("/courses/{courseId}/questions")
    public ResponseEntity<List<QuestionDto>> getAllLessons(
            @PathVariable Long courseId
    ) {
        log.info("Getting all questions by course: {}", courseId);
        List<QuestionEntity> entities = questionService.getQuestionsByCourseId(courseId);

        List<QuestionDto> courseDtos = entities.stream()
                .map(questionMapper::toQuestionDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(courseDtos);
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<QuestionDto> updateLesson(
            @PathVariable Long id,
            @RequestBody QuestionCreateAndUpdateRequest request
    ) {
        log.info("Full update for question id {}: {}", id, request);
        QuestionEntity entity = questionService.updateQuestion(id, request);
        return ResponseEntity.ok(questionMapper.toQuestionDto(entity));
    }

    @PatchMapping("/questions/{id}")
    public ResponseEntity<QuestionDto> patchLesson(
            @PathVariable Long id,
            @RequestBody QuestionCreateAndUpdateRequest request
    ) {
        log.info("Partial update (patch) for question id {}: {}", id, request);

        QuestionEntity entity = questionService.patchQuestion(id, request);
        return ResponseEntity.ok(questionMapper.toQuestionDto(entity));
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<QuestionDto> deleteLesson(@PathVariable Long id) {
        log.info("Deleting question by id: {}", id);

        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
