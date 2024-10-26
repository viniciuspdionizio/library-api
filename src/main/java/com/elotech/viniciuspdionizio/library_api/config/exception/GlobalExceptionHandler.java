package com.elotech.viniciuspdionizio.library_api.config.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        var message = e.getLocalizedMessage();
        if (e.getCause() instanceof UnrecognizedPropertyException ex) {
            message = "Propriedade '%s' não reconhecida".formatted(ex.getPropertyName());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StandardError(400,
                        message,
                        message,
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(
            MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<ObjectError> objectErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        Map<String, String> errors = fieldErrors.stream()
                .collect(Collectors
                        .toMap(FieldError::getField, FieldError::getDefaultMessage));
        errors.putAll(objectErrors.stream().collect(Collectors.toMap(o -> Arrays.toString((String[]) o.getArguments()[1]), ObjectError::getDefaultMessage)));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new StandardError(400,
                        "Erro de validação",
                        "Erro de validação",
                        request.getRequestURI(),
                        errors)
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        var message = e.getLocalizedMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new StandardError(409, message, message, request.getRequestURI())
        );
    }

}
