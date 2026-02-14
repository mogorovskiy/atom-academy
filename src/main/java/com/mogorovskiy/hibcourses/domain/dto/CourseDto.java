package com.mogorovskiy.hibcourses.domain.dto;

import com.mogorovskiy.hibcourses.domain.CourseComplexityEnum;

import java.util.List;

public record CourseDto(
        Long id,
        Long author_id,
        String title,
        String description,
        CourseComplexityEnum complexity,
        List<LessonDto> lessons
) {
}
