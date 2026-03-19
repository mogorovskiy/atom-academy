package com.mogorovskiy.atomacademy.api.controller.common;

import com.mogorovskiy.atomacademy.api.request.common.create.QuestionCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.dto.FullQuestionDto;
import com.mogorovskiy.atomacademy.domain.dto.ShortQuestionDto;
import com.mogorovskiy.atomacademy.domain.entities.QuestionEntity;
import com.mogorovskiy.atomacademy.domain.mapper.QuestionMapper;
import com.mogorovskiy.atomacademy.service.common.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuestionController {

    private final QuestionMapper questionMapper;
    private final QuestionService questionService;

    @PostMapping("/courses/{courseId}/questions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FullQuestionDto> createQuestion(
            @PathVariable Long courseId,
            @Valid @RequestBody QuestionCreateAndUpdateRequest createRequest
    ) {
        log.info("Creating question: {}", createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(courseId, createRequest));
    }

    @GetMapping("/questions/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<FullQuestionDto> getById(@PathVariable Long id) {
        log.info("Getting question by id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestion(id));
    }

    @GetMapping("/courses/{courseId}/questions")
    public ResponseEntity<List<ShortQuestionDto>> getQuestionsByCourse(
            @PathVariable Long courseId
    ) {
        log.info("Getting all questions by course: {}", courseId);
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionsByCourseId(courseId));
    }

    @PutMapping("/questions/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FullQuestionDto> updateQuestion(
            @PathVariable Long id,
            @Valid @RequestBody QuestionCreateAndUpdateRequest request
    ) {
        log.info("Full update for question id {}: {}", id, request);
        QuestionEntity entity = questionService.updateQuestion(id, request);
        return ResponseEntity.ok(questionMapper.toFullQuestionDto(entity));
    }

    @PatchMapping("/questions/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FullQuestionDto> patchQuestion(
            @PathVariable Long id,
            @Valid @RequestBody QuestionCreateAndUpdateRequest request
    ) {
        log.info("Partial update (patch) for question id {}: {}", id, request);

        QuestionEntity entity = questionService.patchQuestion(id, request);
        return ResponseEntity.ok(questionMapper.toFullQuestionDto(entity));
    }

    @DeleteMapping("/questions/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FullQuestionDto> deleteQuestion(@PathVariable Long id) {
        log.info("Deleting question by id: {}", id);

        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
