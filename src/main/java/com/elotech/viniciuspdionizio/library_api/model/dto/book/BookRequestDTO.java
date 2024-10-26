package com.elotech.viniciuspdionizio.library_api.model.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookRequestDTO(
        @NotBlank String title,
        @NotBlank String author,
        @NotBlank String isbn,
        @NotNull LocalDate publishedDate
) {
}
