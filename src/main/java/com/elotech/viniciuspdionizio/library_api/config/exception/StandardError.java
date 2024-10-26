package com.elotech.viniciuspdionizio.library_api.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError {
    private Integer status;
    private String message;
    private String details;
    private String path;

}
