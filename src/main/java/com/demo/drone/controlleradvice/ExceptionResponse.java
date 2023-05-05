package com.demo.drone.controlleradvice;


import java.time.LocalDateTime;

public record ExceptionResponse(String guid, String errorCode, String message, Integer statusCode, String statusName,
                                String path, String method, LocalDateTime timestamp) {
}
