package com.mogorovskiy.atomacademy.domain.dto;

import java.io.Serializable;

public record FullQuestionDto(
        Long id,
        String question,
        String answer,
        Long courseId
) implements Serializable {
}
