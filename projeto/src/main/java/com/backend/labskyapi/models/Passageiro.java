package com.backend.labskyapi.models;

import com.backend.labskyapi.models.enums.Classificacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "passageiros")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passageiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String cpf;
    @Column(nullable = false)
    String nome;
    @Column(nullable = false)
    LocalDate dataNascimento;
    @Column(nullable = false)
    Classificacao classificacao;
    @Column(nullable = false)
    Integer milhas;
}
