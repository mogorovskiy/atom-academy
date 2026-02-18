package com.mogorovskiy.atomacademy.api.request.create;

public record LessonCreateAndUpdateRequest(
        String title,
        String content
) {
}
