package com.inn.nextDoorIt.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApplicationException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public ApplicationException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
