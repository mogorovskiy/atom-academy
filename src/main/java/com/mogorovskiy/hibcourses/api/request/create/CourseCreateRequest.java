package com.mogorovskiy.hibcourses.api.request.create;

import com.mogorovskiy.hibcourses.domain.CourseComplexityEnum;

public record CourseCreateRequest(
        Long authorId,
        String title,
        String description,
        CourseComplexityEnum complexity
) {
}
