package com.mogorovskiy.atomacademy.domain.dto;

import com.mogorovskiy.atomacademy.domain.CourseComplexitiesEnum;

import java.util.List;

public record CourseDto(
        Long id,
        Long creatorId,
        String title,
        String creatorName,
        String description,
        CourseComplexitiesEnum complexity,
        List<QuestionDto> questions
) {
}
