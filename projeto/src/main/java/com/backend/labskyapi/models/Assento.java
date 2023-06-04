package com.backend.labskyapi.models;

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
    @Column(unique = true)
    String nome;
    @Column(nullable = false, columnDefinition = "boolean default false")
    Boolean ocupado;
    @Column(nullable = false, columnDefinition = "boolean default false")
    Boolean emergencia;
}
