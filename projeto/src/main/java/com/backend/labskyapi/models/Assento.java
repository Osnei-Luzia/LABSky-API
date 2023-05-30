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
    String nome;
    @Column(nullable = false)
    Boolean ocupado;
}
