package com.backend.labskyapi.services;

import com.backend.labskyapi.controller.dtos.PassageiroCPFResponseDTO;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    public PassageiroCPFResponseDTO procurarPassageirosByCpf(String cpf) {
        return mapper.mapCpf(repository.findByCpf(cpf));
    }

    public PassagemResponseDTO confirmarPassageiro(PassagemRequestDTO request) {
        Passageiro passageiro = repository.findByCpf(request.getCpf());
        validarPassageiro(passageiro);

        Assento assento = assentoService.procurarAssentoByNome(request.getAssentoNome());
        validarAssento(assento, passageiro, request.getMalasDespachadas());

        assento.setOcupado(true);
        assentoService.ocuparAssento(assento);

        Passagem passagem = passagemMapper.map(request);

        passagem.setAssento(assento);
        passagem.setETicket(UUID.randomUUID().toString());
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        passagem.setDataHoraConfirmacao(timestamp.toLocalDateTime());

        passageiro.setMilhas(passageiro.getMilhas() + passageiro.getClassificacao().getValor());
        passagemService.salvarPassagem(passagem);

        log.info("Confirmação feita pelo passageiro de cpf: " + passageiro.getCpf() + " com e-ticket: " + passagem.getETicket());
        return passagemMapper.map(passagem);
    }

    protected Passagem procurarPassagem(String cpf) {
        return passagemService.procurarPassagemByCpf(cpf);
    }

    protected void validarPassageiro(Passageiro passageiro) {
        if (Objects.isNull(passageiro)) {
            throw new CpfNotExistsException("Passageiro não cadastrado");
        }

        Passagem checkin = passagemService.procurarPassagemByCpf(passageiro.getCpf());
        if (!Objects.isNull(checkin)) {
            throw new PassageiroCheckinException("Passageiro já possui check-in ativo");
        }
    }

    protected void validarAssento(Assento assento, Passageiro passageiro, boolean malasDespachadas) {
        if (Objects.isNull(assento)) {
            throw new AssentoNotExistsException("Assento não cadastrado");
        }

        if (assento.getOcupado()) {
            throw new AssentoOcupadoException("Assento indisponível");
        }

        if (assento.getEmergencia()) {
            if (Period.between(passageiro.getDataNascimento(), LocalDate.now()).getYears() < 18) {//verificar dia e mes não usar date
                throw new AssentoEmergenciaException("Não é permitido passageiro menor de idade em assentos da área de emergência - fileiras 5 e 6");
            }
            if (!malasDespachadas) {
                throw new AssentoEmergenciaException("Não é permitido bagagem de mão em assentos de emergência - fileiras 5 e 6");
            }
        }
    }
}
