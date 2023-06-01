package com.backend.labskyapi.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericHandler {
    @ExceptionHandler(CpfNotExistsException.class)
    public static ResponseEntity error404(CpfNotExistsException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(AssentoNotExistsException.class)
    public static ResponseEntity error404(AssentoNotExistsException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(AssentoOcupadoException.class)
    public static ResponseEntity error409(AssentoOcupadoException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(AssentoEmergenciaException.class)
    public static ResponseEntity error400(AssentoEmergenciaException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(PassageiroCheckinException.class)
    public static ResponseEntity error409(PassageiroCheckinException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }
}
