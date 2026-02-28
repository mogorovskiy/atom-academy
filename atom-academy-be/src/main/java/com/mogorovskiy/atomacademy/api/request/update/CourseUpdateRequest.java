package com.mogorovskiy.atomacademy.api.request.update;

import com.mogorovskiy.atomacademy.domain.CourseComplexitiesEnum;

public record CourseUpdateRequest(
        String title,
        String description,
        CourseComplexitiesEnum complexity
) {
}
