package com.mogorovskiy.hibcourses.api;

public record LessonCreateRequest(
        String title,
        String content
) {
}
