package com.backend.labskyapi.Models;

import com.backend.labskyapi.Models.Enums.Classificacao;
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
    String cpf;
    String nome;
    LocalDate dataNascimento;
    Classificacao classificacao;
    Integer milhas;
}
