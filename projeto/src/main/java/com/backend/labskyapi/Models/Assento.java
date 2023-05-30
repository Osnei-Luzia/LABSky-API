package com.backend.labskyapi.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assentos")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    Boolean ocupado;
}