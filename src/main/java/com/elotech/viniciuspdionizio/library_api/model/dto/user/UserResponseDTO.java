package com.elotech.viniciuspdionizio.library_api.model.dto.user;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String phoneNumber,
        LocalDateTime createdAt
) {
}
