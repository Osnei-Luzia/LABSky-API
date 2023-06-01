package com.backend.labskyapi.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PassagemRequestDTO {
    @NotBlank
    String cpf;
    @NotBlank
    String assentoNome;
    @NotNull
    Boolean malasDespachadas;
}
