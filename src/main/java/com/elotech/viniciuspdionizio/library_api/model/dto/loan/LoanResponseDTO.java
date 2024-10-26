package com.elotech.viniciuspdionizio.library_api.model.dto.loan;

import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserResponseDTO;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.time.LocalDate;

public record LoanResponseDTO(
        Boolean status,
        Integer id,
        @JsonIncludeProperties({"id", "name"})
        UserResponseDTO user,
        @JsonIncludeProperties({"id", "title"})
        BookResponseDTO book,
        LocalDate loanDate,
        LocalDate returnDate
) {
}
