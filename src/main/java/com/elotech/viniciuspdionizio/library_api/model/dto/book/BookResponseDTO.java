package com.elotech.viniciuspdionizio.library_api.model.dto.book;

import com.elotech.viniciuspdionizio.library_api.model.enums.BookCategory;

import java.time.LocalDate;

public record BookResponseDTO(
        Integer id,
        String title,
        String author,
        String isbn,
        LocalDate publishedDate,
        BookCategory category) {
}
