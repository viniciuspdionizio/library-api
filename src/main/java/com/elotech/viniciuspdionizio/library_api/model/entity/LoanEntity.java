package com.elotech.viniciuspdionizio.library_api.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LoanEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "loan_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @NotNull
    @PastOrPresent
    private LocalDate loanDate = LocalDate.now();

    @NotNull
    private LocalDate returnDate;

    @NotNull
    private Boolean status = true;

}
