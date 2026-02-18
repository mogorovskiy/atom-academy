package com.mogorovskiy.hibcourses.api.request.create;

public record AuthorCreateAndUpdateRequest(
        String name,
        String email
) {
}
