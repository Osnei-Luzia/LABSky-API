package com.backend.labskyapi.services;

import com.backend.labskyapi.models.Assento;
import com.backend.labskyapi.repositories.AssentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssentoService {
    private final AssentoRepository repository;

    public AssentoService(AssentoRepository repository) {
        this.repository = repository;
    }

    public List<Assento> procurarAssento() {
        return repository.findAll();
    }

    public Assento procurarAssentoByNome(String nome) {
        return repository.findAssentoByNome(nome);
    }

    public void ocuparAssento(Assento assento) {
        repository.save(assento);
    }
}
