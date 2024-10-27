package com.elotech.viniciuspdionizio.library_api.controller.impl;

import com.elotech.viniciuspdionizio.library_api.controller.LoanController;
import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanResponseDTO;
import com.elotech.viniciuspdionizio.library_api.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanRestController implements LoanController {

    private final LoanService loanService;

    @Override
    @GetMapping("/page")
    public Page<LoanResponseDTO> getAll(@RequestParam(required = false) Integer userId,
                                        @RequestParam(required = false) Integer bookId,
                                        @RequestParam(defaultValue = "true") Boolean status,
                                        Pageable pageable) {
        return this.loanService.getAll(userId, bookId, status, pageable);
    }

    @Override
    @PostMapping
    public ResponseEntity<LoanResponseDTO> register(@RequestBody @Valid LoanRequestDTO loanRequestDTO) {
        var resp = this.loanService.register(loanRequestDTO);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resp.id()).toUri();
        return ResponseEntity.created(uri).body(resp);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> update(@PathVariable Integer id,
                                                  @RequestParam(required = false) Boolean status,
                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        return ResponseEntity.ok(this.loanService.update(id, status, returnDate));
    }

}
