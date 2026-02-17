package com.mogorovskiy.hibcourses.api.controller;

import com.mogorovskiy.hibcourses.api.LessonCreateRequest;
import com.mogorovskiy.hibcourses.domain.dto.LessonDto;
import com.mogorovskiy.hibcourses.domain.entities.LessonEntity;
import com.mogorovskiy.hibcourses.domain.mapper.LessonMapper;
import com.mogorovskiy.hibcourses.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LessonController {

    private final LessonService lessonService;
    private final LessonMapper lessonMapper;

    @PostMapping("/course/{courseId}/lessons")
    public ResponseEntity<LessonDto> createLesson(
            @PathVariable Long courseId,
            @RequestBody LessonCreateRequest createRequest
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

    //TODO: add update & patch

    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<LessonDto> deleteLesson(@PathVariable Long id) {
        log.info("Deleting lesson by id: {}", id);

        lessonService.deleteLesson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
