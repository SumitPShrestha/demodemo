package com.demo.drone.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DroneStateException extends RuntimeException {

    String message;


}
