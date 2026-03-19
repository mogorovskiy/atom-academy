package com.mogorovskiy.atomacademy.domain.dto;

import java.io.Serializable;

public record ShortQuestionDto(
        Long id,
        String question,
        Long courseId
) implements Serializable {
}
