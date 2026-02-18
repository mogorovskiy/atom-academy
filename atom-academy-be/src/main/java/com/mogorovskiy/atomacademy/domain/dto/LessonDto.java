package com.mogorovskiy.atomacademy.domain.dto;

public record LessonDto(
        String title,
        String content,
        Long courseId
) {
}
