package com.backend.labskyapi.controller.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PassagemResponseDTO {
    String eTicket;
    //mudar para algum date
    LocalDateTime dataHoraConfirmacao;
}
