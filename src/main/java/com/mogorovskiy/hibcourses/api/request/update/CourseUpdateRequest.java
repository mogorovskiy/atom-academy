package com.mogorovskiy.hibcourses.api.request.update;

import com.mogorovskiy.hibcourses.domain.CourseComplexityEnum;

public record CourseUpdateRequest(
        String title,
        String description,
        CourseComplexityEnum complexity
) {
}
