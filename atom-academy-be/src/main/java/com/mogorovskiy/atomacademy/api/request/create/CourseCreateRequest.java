package com.mogorovskiy.atomacademy.api.request.create;

import com.mogorovskiy.atomacademy.domain.CourseComplexityEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CourseCreateRequest(

        @NotBlank(message = "Author id cannot be empty")
        Long authorId,

        @NotBlank(message = "Title cannot be empty")
        @Size(min = 10, max = 500, message = "Title must be between 10 and 500 characters")
        String title,

        @NotBlank(message = "Description cannot be empty")
        String description,

        CourseComplexityEnum complexity

) {
}
