package com.backend.labskyapi.controller.dtos;

import com.backend.labskyapi.models.Assento;
import lombok.Data;

@Data
public class PassagemRequestDTO {
    String cpf;
    Assento assento;
    Boolean malasDespachadas;
}
