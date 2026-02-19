package com.mogorovskiy.atomacademy.api.controller;

import com.mogorovskiy.atomacademy.api.request.create.LessonCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.dto.LessonDto;
import com.mogorovskiy.atomacademy.domain.entities.LessonEntity;
import com.mogorovskiy.atomacademy.domain.mapper.LessonMapper;
import com.mogorovskiy.atomacademy.service.LessonService;
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
public class LessonController {

    private final LessonService lessonService;
    private final LessonMapper lessonMapper;

    @PostMapping("/courses/{courseId}/lessons")
    public ResponseEntity<LessonDto> createLesson(
            @PathVariable Long courseId,
            @RequestBody LessonCreateAndUpdateRequest createRequest
    ) {
        log.info("Creating lesson: {}", createRequest);

        LessonEntity entity = lessonService.createLesson(courseId, createRequest);
        LessonDto lessonDto = lessonMapper.toLessonDto(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(lessonDto);
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<LessonDto> getById(@PathVariable Long id) {
        log.info("Getting lesson by id: {}", id);

        LessonEntity entity = lessonService.getLesson(id);
        LessonDto lessonDto = lessonMapper.toLessonDto(entity);

        return ResponseEntity.status(HttpStatus.OK).body(lessonDto);
    }

    @GetMapping("/courses/{courseId}/lessons")
    public ResponseEntity<List<LessonDto>> getAllLessons(
            @PathVariable Long courseId
    ) {
        log.info("Getting all lessons");
        List<LessonEntity> entities = lessonService.getLessonsByCourseId(courseId);

        List<LessonDto> courseDtos = entities.stream()
                .map(lessonMapper::toLessonDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(courseDtos);
    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<LessonDto> updateLesson(
            @PathVariable Long id,
            @RequestBody LessonCreateAndUpdateRequest request
    ) {
        log.info("Full update for lesson id {}: {}", id, request);

        LessonEntity entity = lessonService.updateLesson(id, request);
        return ResponseEntity.ok(lessonMapper.toLessonDto(entity));
    }

    @PatchMapping("/lessons/{id}")
    public ResponseEntity<LessonDto> patchLesson(
            @PathVariable Long id,
            @RequestBody LessonCreateAndUpdateRequest request
    ) {
        log.info("Partial update (patch) for lesson id {}: {}", id, request);

        LessonEntity entity = lessonService.patchLesson(id, request);
        return ResponseEntity.ok(lessonMapper.toLessonDto(entity));
    }

    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<LessonDto> deleteLesson(@PathVariable Long id) {
        log.info("Deleting lesson by id: {}", id);

        lessonService.deleteLesson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
