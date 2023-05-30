package com.backend.labskyapi.services;

import com.backend.labskyapi.models.Passagem;
import com.backend.labskyapi.repositories.PassagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassagemService {
    PassagemRepository repository;

    public PassagemService(PassagemRepository repository) {
        this.repository = repository;
    }

    public Passagem procurarPassagemByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
