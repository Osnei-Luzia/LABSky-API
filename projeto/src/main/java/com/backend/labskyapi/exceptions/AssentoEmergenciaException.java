package com.backend.labskyapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssentoEmergenciaException extends IllegalArgumentException {
    private String message;
}
