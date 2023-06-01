package com.backend.labskyapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassageiroCheckinException extends IllegalArgumentException {
    private String message;
}
