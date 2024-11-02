package com.utn.airbnb.application.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnauthorizedException extends RuntimeException {
    private final String message;
    public UnauthorizedException(String mensaje) {
        this.message = mensaje;
    }
}
