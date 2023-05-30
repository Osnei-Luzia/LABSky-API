package com.backend.labskyapi.controller.dtos;

import com.backend.labskyapi.models.Assento;
import com.backend.labskyapi.models.Enums.Classificacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassageiroResponseDTO {
    @NotBlank
    String cpf;
    @NotBlank
    String nome;
    @NotNull
    LocalDate dataNascimento;
    @NotNull
    Classificacao classificacao;
    @NotNull
    Integer milhas;
    String eTicket;
    Assento assento;
    Boolean malasDespachadas;
    //mudar para LocalDateTime
    String dataHora;
}
