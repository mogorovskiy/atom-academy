package com.mogorovskiy.atomacademy.api.request.update;

import com.mogorovskiy.atomacademy.domain.CourseComplexityEnum;

public record CourseUpdateRequest(
        String title,
        String description,
        CourseComplexityEnum complexity
) {
}
