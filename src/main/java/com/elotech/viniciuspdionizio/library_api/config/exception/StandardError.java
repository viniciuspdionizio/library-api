package com.elotech.viniciuspdionizio.library_api.config.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StandardError {
    private Integer status;
    private String message;
    private String details;
    private String path;
    private final Map<String, String> fields = new HashMap<>();

    public StandardError(Integer status, String message, String details, String path, Map<String, String> fields) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.path = path;
        this.fields.putAll(fields);
    }
}
