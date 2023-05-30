package com.backend.labskyapi.Models;

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
    String eTicket;
    @Column(nullable = false)
    String Assento;
    @Column(nullable = false)
    Boolean malas;
    @Column(nullable = false)
    LocalDateTime dataHora;
}
