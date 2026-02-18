package com.mogorovskiy.hibcourses.api.request.create;

public record LessonCreateAndUpdateRequest(
        String title,
        String content
) {
}
