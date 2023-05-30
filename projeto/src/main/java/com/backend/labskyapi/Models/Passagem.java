package com.backend.labskyapi.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Passagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String eTicket;
    String Assento;
    Boolean malas;
    LocalDateTime dataHora;
}
