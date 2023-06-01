package com.backend.labskyapi.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PassagemResponseDTO {
    @NotBlank
    String eTicket;
    @NotNull
    LocalDateTime dataHoraConfirmacao;
}
