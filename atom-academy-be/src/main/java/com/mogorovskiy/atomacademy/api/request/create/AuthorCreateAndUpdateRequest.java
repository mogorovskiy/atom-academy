package com.mogorovskiy.atomacademy.api.request.create;

public record AuthorCreateAndUpdateRequest(
        String name,
        String email
) {
}
