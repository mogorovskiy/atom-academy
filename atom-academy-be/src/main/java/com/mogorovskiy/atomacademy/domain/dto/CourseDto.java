package com.mogorovskiy.atomacademy.domain.dto;

import com.mogorovskiy.atomacademy.domain.CourseComplexityEnum;

import java.util.List;

public record CourseDto(
        Long id,
        Long authorId,
        String title,
        String authorName,
        String description,
        CourseComplexityEnum complexity,
        List<QuestionDto> questions
) {
}
