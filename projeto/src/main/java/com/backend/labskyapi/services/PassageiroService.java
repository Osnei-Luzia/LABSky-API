package com.backend.labskyapi.services;

import com.backend.labskyapi.controller.dtos.PassagemRequestDTO;
import com.backend.labskyapi.controller.dtos.PassagemResponseDTO;
import com.backend.labskyapi.controller.dtos.PassageiroResponseDTO;
import com.backend.labskyapi.exceptions.*;
import com.backend.labskyapi.models.Assento;
import com.backend.labskyapi.models.Passageiro;
import com.backend.labskyapi.models.Passagem;
import com.backend.labskyapi.repositories.PassageiroRepository;
import com.backend.labskyapi.services.mappers.PassageiroMapper;
import com.backend.labskyapi.services.mappers.PassagemMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
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
    AssentoService assentoService;

    public PassageiroService(PassageiroRepository repository, PassagemService passagemService, PassageiroMapper mapper, PassagemMapper passagemMapper, AssentoService assentoService) {
        this.repository = repository;
        this.passagemService = passagemService;
        this.mapper = mapper;
        this.passagemMapper = passagemMapper;
        this.assentoService = assentoService;
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

    public PassagemResponseDTO confirmarPassageiro(PassagemRequestDTO request) {
        Passageiro passageiro = repository.findByCpf(request.getCpf());
        if (Objects.isNull(passageiro)) {
            throw new CpfNotExistsException("Passageiro não cadastrado");
        }
        Passagem checkin = passagemService.procurarPassagemByCpf(passageiro.getCpf());
        if (!Objects.isNull(checkin)) {
            throw new PassageiroCheckinException("Passageiro já possui check-in ativo");
        }//verificar se passageiro pode ter dois checkins, ou seja, comprar duas passagens antecipadamente

        Assento assento = assentoService.procurarAssentoByNome(request.getAssentoNome());
        if (Objects.isNull(assento)) {
            throw new AssentoNotExistsException("Assento não cadastrado");
        }
        if (assento.getOcupado()) {
            throw new AssentoOcupadoException("Assento indisponível");
        }
        //verificar maneira melhor de verificar true
        if (assento.getEmergencia() == true) {
            if ((passageiro.getDataNascimento().getYear() - (new Date().getYear() + 1900) < 18)) {
                throw new AssentoEmergenciaException("Não é permitido passageiro menor de idade em assentos da área de emergência - fileiras 5 e 6");
            }
            //verificar maneira melhor de verificar false
            if (request.getMalasDespachadas() == false) {
                throw new AssentoEmergenciaException("Não é permitido bagagem de mão em assentos de emergência - fileiras 5 e 6");
            }
        }
        Passagem passagem = passagemMapper.map(request);

        assento.setOcupado(true);
        assentoService.ocuparAssento(assento);

        passagem.setAssento(assento);
        passagem.setETicket(UUID.randomUUID().toString());
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        passagem.setDataHoraConfirmacao(timestamp.toLocalDateTime());
        System.out.println(passagem.getDataHoraConfirmacao());//teste de formato da hora

        switch (passageiro.getClassificacao()) {
            case VIP -> passageiro.setMilhas(passageiro.getMilhas() + 100);
            case OURO -> passageiro.setMilhas(passageiro.getMilhas() + 80);
            case PRATA -> passageiro.setMilhas(passageiro.getMilhas() + 50);
            case BRONZE -> passageiro.setMilhas(passageiro.getMilhas() + 30);
            case ASSOCIADO -> passageiro.setMilhas(passageiro.getMilhas() + 10);
        }
        System.out.println(passageiro.getMilhas());//teste de milhas

        passagemService.salvarPassagem(passagem);
        System.out.println("Confirmação feita pelo passageiro de cpf: " + passageiro.getCpf() + " com e-ticket: " + passagem.getETicket());
        return passagemMapper.map(passagem);

    }

    /*
    String cpf;
    Assento assento;
    Boolean malasDespachadas;
    String eTicket;
    String dataHora;
    */

    private Passagem procurarPassagem(String cpf) {
        return passagemService.procurarPassagemByCpf(cpf);
    }
}
