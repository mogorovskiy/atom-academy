package com.mogorovskiy.atomacademy.api.request.create;

import com.mogorovskiy.atomacademy.domain.CourseComplexitiesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CourseCreateRequest(

        @NotBlank(message = "Creator id cannot be empty")
        Long creatorId,

        @NotBlank(message = "Title cannot be empty")
        @Size(min = 10, max = 500, message = "Title must be between 10 and 500 characters")
        String title,

        @NotBlank(message = "Description cannot be empty")
        String description,

        CourseComplexitiesEnum complexity

) {
}
