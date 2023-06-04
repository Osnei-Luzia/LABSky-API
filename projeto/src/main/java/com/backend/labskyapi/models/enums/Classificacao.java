package com.backend.labskyapi.models.enums;

import lombok.Getter;

public enum Classificacao {
    VIP(100),
    OURO(80),
    PRATA(50),
    BRONZE(30),
    ASSOCIADO(10);

    @Getter
    private Integer valor;

    Classificacao(Integer i) {
        this.valor = i;
    }
}
