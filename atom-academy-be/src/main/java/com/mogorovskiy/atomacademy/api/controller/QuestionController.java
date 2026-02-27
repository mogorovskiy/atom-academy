package com.mogorovskiy.atomacademy.api.controller;

import com.mogorovskiy.atomacademy.api.request.create.QuestionCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.dto.QuestionDto;
import com.mogorovskiy.atomacademy.domain.entities.QuestionEntity;
import com.mogorovskiy.atomacademy.domain.mapper.QuestionMapper;
import com.mogorovskiy.atomacademy.service.QuestionService;
import jakarta.validation.Valid;
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
            @Valid @RequestBody QuestionCreateAndUpdateRequest createRequest
    ) {
        log.info("Creating question: {}", createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(courseId, createRequest));
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<QuestionDto> getById(@PathVariable Long id) {
        log.info("Getting question by id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestion(id));
    }

    @GetMapping("/courses/{courseId}/questions")
    public ResponseEntity<List<QuestionDto>> getQuestionsByCourse(
            @PathVariable Long courseId
    ) {
        log.info("Getting all questions by course: {}", courseId);
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionsByCourseId(courseId));
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(
            @PathVariable Long id,
            @Valid @RequestBody QuestionCreateAndUpdateRequest request
    ) {
        log.info("Full update for question id {}: {}", id, request);
        QuestionEntity entity = questionService.updateQuestion(id, request);
        return ResponseEntity.ok(questionMapper.toQuestionDto(entity));
    }

    @PatchMapping("/questions/{id}")
    public ResponseEntity<QuestionDto> patchQuestion(
            @PathVariable Long id,
            @Valid @RequestBody QuestionCreateAndUpdateRequest request
    ) {
        log.info("Partial update (patch) for question id {}: {}", id, request);

        QuestionEntity entity = questionService.patchQuestion(id, request);
        return ResponseEntity.ok(questionMapper.toQuestionDto(entity));
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<QuestionDto> deleteQuestion(@PathVariable Long id) {
        log.info("Deleting question by id: {}", id);

        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
