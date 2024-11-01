package com.utn.airbnb.application.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BadRequestException extends RuntimeException {
    private final String message;
    public BadRequestException(String mensaje) {
        this.message = mensaje;
    }
}
