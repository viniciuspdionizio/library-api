package com.elotech.viniciuspdionizio.library_api.controller;

import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface LoanController {

    Page<LoanResponseDTO> getAll(Integer userId, Integer bookId, Boolean status, Pageable pageable);

    ResponseEntity<LoanResponseDTO> register(LoanRequestDTO loanRequestDTO);

    ResponseEntity<LoanResponseDTO> update(Integer id, Boolean status, LocalDate returnDate);

}
