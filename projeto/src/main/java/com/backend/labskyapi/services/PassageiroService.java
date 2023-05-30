package com.backend.labskyapi.services;

import com.backend.labskyapi.controller.dtos.PassageiroResponseDTO;
import com.backend.labskyapi.models.Passagem;
import com.backend.labskyapi.repositories.PassageiroRepository;
import com.backend.labskyapi.repositories.PassagemRepository;
import com.backend.labskyapi.services.mappers.PassageiroMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PassageiroService {
    PassageiroRepository repository;
    PassagemService passagemService;
    PassageiroMapper mapper;

    public PassageiroService(PassageiroRepository repository, PassagemService passagemService, PassageiroMapper mapper) {
        this.repository = repository;
        this.passagemService = passagemService;
        this.mapper = mapper;
    }

    public List<PassageiroResponseDTO> procurarPassageiros(){
        List<PassageiroResponseDTO> passageiros = repository.findAll().stream().map(passageiro -> mapper.map(passageiro)).collect(Collectors.toList());
        for(PassageiroResponseDTO passageiro: passageiros){
            Passagem passagem = passagemService.procurarPassagemByCpf(passageiro.getCpf());
            if(!Objects.isNull(passagem)){
                mapper.update(passageiro,passagem);
            }
        }
        return passageiros;
    }
}
