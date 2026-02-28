package com.mogorovskiy.atomacademy.api.request.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateAndUpdateRequest(

        @NotBlank(message = "Name cannot be empty")
        @Size(min = 10, max = 500, message = "Name must be between 10 and 500 characters")
        String name,

        @Email
        @NotBlank(message = "Email cannot be empty")
        @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
        String email

) {
}
