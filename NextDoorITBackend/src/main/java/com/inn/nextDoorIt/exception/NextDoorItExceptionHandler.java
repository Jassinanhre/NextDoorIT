package com.inn.nextDoorIt.exception;

import com.inn.nextDoorIt.utils.ApplicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NextDoorItExceptionHandler {
    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<Object> handleAllNextDoorItExceptions(ApplicationException e) {
        return ResponseEntity.status(e.getStatus().value()).body(new ApplicationResponse(e.getStatus().value(), e.getMessage()));
    }
}
