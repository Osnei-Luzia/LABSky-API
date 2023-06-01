package com.backend.labskyapi.services;

import com.backend.labskyapi.controller.dtos.PassagemRequestDTO;
import com.backend.labskyapi.controller.dtos.PassagemResponseDTO;
import com.backend.labskyapi.controller.dtos.PassageiroResponseDTO;
import com.backend.labskyapi.models.Passagem;
import com.backend.labskyapi.repositories.PassageiroRepository;
import com.backend.labskyapi.services.mappers.PassageiroMapper;
import com.backend.labskyapi.services.mappers.PassagemMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PassageiroService {
    PassageiroRepository repository;
    PassagemService passagemService;
    PassageiroMapper mapper;
    PassagemMapper passagemMapper;

    public PassageiroService(PassageiroRepository repository, PassagemService passagemService, PassageiroMapper mapper, PassagemMapper passagemMapper) {
        this.repository = repository;
        this.passagemService = passagemService;
        this.mapper = mapper;
        this.passagemMapper = passagemMapper;
    }

    public List<PassageiroResponseDTO> procurarPassageiros() {
        List<PassageiroResponseDTO> passageiros = repository.findAll().stream().map(passageiro -> mapper.map(passageiro)).collect(Collectors.toList());
        for (PassageiroResponseDTO passageiro : passageiros) {
            Passagem passagem = procurarPassagem(passageiro.getCpf());
            if (!Objects.isNull(passagem)) {
                mapper.update(passageiro, passagem);
            }
        }
        return passageiros;
    }

    public PassageiroResponseDTO procurarPassageirosByCpf(String cpf) {
        PassageiroResponseDTO passageiro = mapper.map(repository.findByCpf(cpf));
        Passagem passagem = procurarPassagem(passageiro.getCpf());
        if (!Objects.isNull(passagem)) {
            mapper.update(passageiro, passagem);
        }
        return passageiro;
    }
    public PassagemResponseDTO confirmarPassageiro(PassagemRequestDTO request){
        Passagem passagem = passagemMapper.map(request);

        passagem.setETicket(UUID.randomUUID().toString());

        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        passagem.setDataHoraConfirmacao(timestamp.toLocalDateTime());
        System.out.println(passagem.getDataHoraConfirmacao());//teste de formato da hora

        passagemService.salvarPassagem(passagem);
        return passagemMapper.map(passagem);
    }

    /*
    String cpf;
    Assento assento;
    Boolean malasDespachadas;
    String eTicket;
    String dataHora;
    */

    private Passagem procurarPassagem(String cpf){
        return passagemService.procurarPassagemByCpf(cpf);
    }
}
