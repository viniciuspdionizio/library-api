package com.elotech.viniciuspdionizio.library_api.config.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new StandardError(404,
                        "%s não encontrado".formatted(e.getEntityName()),
                        "%s não encontrado com o id #%s".formatted(e.getEntityName(), e.getIdentifier()),
                        request.getRequestURI()
                ));
    }

}
