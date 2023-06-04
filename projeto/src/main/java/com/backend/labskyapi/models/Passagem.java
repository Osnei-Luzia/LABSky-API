package com.backend.labskyapi.models;

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
    @Column(nullable = false, unique = true)
    String eTicket;
    @OneToOne
    Assento assento;
    @Column(nullable = false)
    Boolean malasDespachadas;
    @Column(nullable = false)
    LocalDateTime dataHoraConfirmacao;
}
