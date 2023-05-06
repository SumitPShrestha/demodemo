package com.demo.drone.controlleradvice;

import com.demo.drone.exception.DroneRegistrationException;
import com.demo.drone.exception.DroneStateException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DroneRegistrationException.class)
    public ResponseEntity<?> handleApplicationException(
            final DroneRegistrationException exception, final HttpServletRequest request
    ) {
        var guid = UUID.randomUUID().toString();
        var response = new ExceptionResponse(
                guid,
                exception.getErrorCode(),
                exception.getMessage(),
                exception.getHttpStatus().value(),
                exception.getHttpStatus().name(),
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler(DroneStateException.class)
    public ResponseEntity<?> handleUnknownException(
            final Exception exception, final HttpServletRequest request
    ) {


        return new ResponseEntity<>(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

}
