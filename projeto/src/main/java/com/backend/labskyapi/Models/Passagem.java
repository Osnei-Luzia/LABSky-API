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
    String eTicket;
    String Assento;
    Boolean malas;
    LocalDateTime dataHora;
}
