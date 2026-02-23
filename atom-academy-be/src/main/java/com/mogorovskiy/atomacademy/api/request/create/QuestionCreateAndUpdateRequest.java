package com.mogorovskiy.atomacademy.api.request.create;

public record QuestionCreateAndUpdateRequest(
        String question,
        String answer
) {
}
