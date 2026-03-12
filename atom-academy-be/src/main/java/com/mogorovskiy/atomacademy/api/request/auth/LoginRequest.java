package com.mogorovskiy.atomacademy.api.request.auth;

public record LoginRequest(
        String email,
        String password
) {
}
