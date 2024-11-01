package com.utn.airbnb.application.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotFoundException extends RuntimeException {
    private final String message;
    public NotFoundException(String mensaje) {
        this.message = mensaje;
    }
}
