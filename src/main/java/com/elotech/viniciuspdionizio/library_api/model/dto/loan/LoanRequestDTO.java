package com.elotech.viniciuspdionizio.library_api.model.dto.loan;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record LoanRequestDTO(
        @NotNull Integer userId,
        @NotNull Integer bookId,
        @NotNull @PastOrPresent LocalDate loanDate,
        @NotNull LocalDate returnDate
) {
}
