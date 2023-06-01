package com.backend.labskyapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "passagens")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String cpf;
    @Column(nullable = false)
    String eTicket;
    @OneToOne
    Assento assento;
    @Column(nullable = false)
    Boolean malasDespachadas;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    //checar formatação
    LocalDateTime dataHoraConfirmacao;
}
