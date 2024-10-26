package com.elotech.viniciuspdionizio.library_api.controller;

import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanResponseDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface LoanController {

    ResponseEntity<LoanResponseDTO> register(LoanRequestDTO loanRequestDTO);

    ResponseEntity<LoanResponseDTO> update(Integer id, Boolean status, LocalDate returnDate);

}
