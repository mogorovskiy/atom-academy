package com.mogorovskiy.hibcourses.api;

import com.mogorovskiy.hibcourses.domain.CourseComplexityEnum;

public record CourseCreateRequest(
        Long author_id,
        String title,
        String description,
        CourseComplexityEnum complexity
) {
}
