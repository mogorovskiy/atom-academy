package com.mogorovskiy.hibcourses.domain.dto;

import com.mogorovskiy.hibcourses.domain.CourseComplexityEnum;

import java.util.List;

public record CourseDto(
        Long id,
        Long authorId,
        String title,
        String authorName,
        String description,
        CourseComplexityEnum complexity,
        List<LessonDto> lessons
) {
}
