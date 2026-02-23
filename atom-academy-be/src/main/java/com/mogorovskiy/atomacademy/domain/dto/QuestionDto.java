package com.mogorovskiy.atomacademy.domain.dto;

public record QuestionDto(
        String question,
        String answer,
        Long courseId
) {
}
