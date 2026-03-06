package com.mogorovskiy.atomacademy.domain.dto;

import java.io.Serializable;

public record QuestionDto(
        String question,
        String answer,
        Long courseId
) implements Serializable {
}
