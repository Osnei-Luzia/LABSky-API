package com.backend.labskyapi.controller.dtos;

import com.backend.labskyapi.models.enums.Classificacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassageiroCPFResponseDTO {
    @NotBlank
    String cpf;
    @NotBlank
    String nome;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataNascimento;
    @NotNull
    Classificacao classificacao;
    @NotNull
    Integer milhas;
}