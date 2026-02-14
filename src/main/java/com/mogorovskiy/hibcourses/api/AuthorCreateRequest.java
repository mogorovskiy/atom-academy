package com.mogorovskiy.hibcourses.api;

public record AuthorCreateRequest(
        String name,
        String email
) {
}
