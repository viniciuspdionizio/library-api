package com.elotech.viniciuspdionizio.library_api.config.exception;

import lombok.Data;

@Data
public class LoanExistsActiveException extends RuntimeException {

    public LoanExistsActiveException() {
        super("Existem empréstimos ativos para este usuário/livro");
    }
}
