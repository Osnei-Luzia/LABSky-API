package com.backend.labskyapi.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassagemRequestDTO {
    @NotBlank
    String cpf;
    @NotBlank
    String assentoNome;
    @NotNull
    Boolean malasDespachadas;
}
