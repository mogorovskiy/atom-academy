package com.mogorovskiy.atomacademy.api.request.create;

import com.mogorovskiy.atomacademy.domain.CourseComplexityEnum;

public record CourseCreateRequest(
        Long authorId,
        String title,
        String description,
        CourseComplexityEnum complexity
) {
}
