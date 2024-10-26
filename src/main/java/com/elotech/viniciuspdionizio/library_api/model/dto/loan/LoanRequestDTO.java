package com.elotech.viniciuspdionizio.library_api.model.dto.loan;

import java.time.LocalDate;

public record LoanRequestDTO(
        Integer userId,
        Integer bookId,
        LocalDate loanDate,
        LocalDate returnDate
) {
}
