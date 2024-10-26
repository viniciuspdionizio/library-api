package com.elotech.viniciuspdionizio.library_api.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum BookCategory {
    FICTION("Ficção"),
    NON_FICTION("Não-Ficção"),
    SCIENCE("Ciência"),
    FANTASY("Fantasia"),
    BIOGRAPHY("Biografia"),
    MYSTERY("Mistério"),
    HISTORY("História"),
    ROMANCE("Romance"),
    THRILLER("Suspense"),
    CHILDREN("Infantil"),
    DEFAULT("Não especificado");

    @Getter
    @JsonValue
    private final String displayName;

    BookCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
