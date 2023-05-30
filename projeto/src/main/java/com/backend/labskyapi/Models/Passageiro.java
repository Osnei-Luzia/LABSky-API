package com.backend.labskyapi.Models;

import com.backend.labskyapi.Models.Enums.Classificacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Passageiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String cpf;
    String nome;
    LocalDate dataNascimento;
    Classificacao classificacao;
    Integer milhas;
}
