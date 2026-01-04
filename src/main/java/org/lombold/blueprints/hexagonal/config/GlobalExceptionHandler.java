package org.lombold.blueprints.hexagonal.config;

import org.lombold.blueprints.hexagonal.adapter.secondary.persistency.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            NotFoundException.class,
            IllegalArgumentException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgument(final RuntimeException ex) {
        return new ErrorResponse("bad_request", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneric(final Exception ex) {
        return new ErrorResponse("internal_error", "Unexpected error");
    }

    public record ErrorResponse(String code, String message) {
    }
}