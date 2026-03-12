package com.mogorovskiy.atomacademy.api.request.common.create;

import jakarta.validation.constraints.NotBlank;

public record QuestionCreateAndUpdateRequest(

        @NotBlank(message = "Question cannot be empty")
        String question,

        String answer

) {
}
