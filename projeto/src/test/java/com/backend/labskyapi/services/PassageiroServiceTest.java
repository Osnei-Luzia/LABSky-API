package com.backend.labskyapi.services;

import com.backend.labskyapi.controller.dtos.PassagemRequestDTO;
import com.backend.labskyapi.exceptions.*;
import com.backend.labskyapi.models.Assento;
import com.backend.labskyapi.models.enums.Classificacao;
import com.backend.labskyapi.models.Passageiro;
import com.backend.labskyapi.models.Passagem;
import com.backend.labskyapi.repositories.PassageiroRepository;
import com.backend.labskyapi.services.mappers.PassageiroMapper;
import com.backend.labskyapi.services.mappers.PassagemMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PassageiroServiceTest {
    @Mock
    PassageiroRepository repository;
    @Mock
    PassagemService passagemService;
    @Mock
    PassageiroMapper mapper;
    @Mock
    PassagemMapper passagemMapper;
    @Mock
    AssentoService assentoService;
    @InjectMocks
    PassageiroService service;

    @Test
    @DisplayName("Quando não encontrar um passageiro por cpf, deve retornar a exceção CpfNotExistsException")
    void passageiro_notFound() {
        PassagemRequestDTO request = new PassagemRequestDTO();

        Passageiro passageiro = repository.findByCpf(request.getCpf());

        assertThrows(CpfNotExistsException.class, () -> service.validarPassageiro(passageiro));
    }

    @Test
    @DisplayName("Quando o passageiro já houver checkin ativo, deve retornar a exceção PassageiroCheckinException")
    void passageiro_conflict() {
        Passageiro passageiro = new Passageiro(2L, "222.222.222-22", "Nome", LocalDate.of(1961, 02, 12), Classificacao.VIP, 100);
        Passagem passagem = new Passagem();
        Mockito.when(passagemService.procurarPassagemByCpf(Mockito.anyString()))
                .thenReturn(passagem);

        assertThrows(PassageiroCheckinException.class, () -> service.validarPassageiro(passageiro));
    }

    @Test
    @DisplayName("Quando não encontrar um assento por nome, deve retornar a exceção PassageiroCheckinException")
    void assento_notFound() {
        PassagemRequestDTO request = new PassagemRequestDTO("222.222.222-22", "2A", true);
        Passageiro passageiro = new Passageiro(2L, "222.222.222-22", "Nome", LocalDate.of(1961, 02, 12), Classificacao.VIP, 100);

        Assento assentoReal = assentoService.procurarAssentoByNome(request.getAssentoNome());

        assertThrows(AssentoNotExistsException.class, () -> service.validarAssento(assentoReal, passageiro, request.getMalasDespachadas()));
    }

    @Test
    @DisplayName("Quando o assento possuir a propriedade ocupado true, deve retornar a exceção AssentoOcupadoException")
    void assento_conflict() {
        PassagemRequestDTO request = new PassagemRequestDTO("222.222.222-22", "2A", true);
        Passageiro passageiro = new Passageiro(2L, "222.222.222-22", "Nome", LocalDate.of(1961, 02, 12), Classificacao.VIP, 100);
        Assento assento = new Assento(1L, "1A", true, false);

        assertThrows(AssentoOcupadoException.class, () -> service.validarAssento(assento, passageiro, request.getMalasDespachadas()));
    }

    @Test
    @DisplayName("Quando o assento possuir a propriedade emergencia true e a idade do passageiro for menor que 18, deve retornar a exceção AssentoEmergenciaException")
    void assento_emergenciaIdade() {
        PassagemRequestDTO request = new PassagemRequestDTO("222.222.222-22", "5A", true);
        Passageiro passageiro = new Passageiro(2L, "222.222.222-22", "Nome", LocalDate.now(), Classificacao.VIP, 100);
        Assento assento = new Assento(5L, "5A", false, true);

        assertThrows(AssentoEmergenciaException.class, () -> service.validarAssento(assento, passageiro, request.getMalasDespachadas()));
    }

    @Test
    @DisplayName("Quando o assento possuir a propriedade emergencia true e o request possuir malasDespachadas false, deve retornar a exceção AssentoEmergenciaException")
    void assento_emergenciaMalas() {
        PassagemRequestDTO request = new PassagemRequestDTO("222.222.222-22", "5A", false);
        Passageiro passageiro = new Passageiro(2L, "222.222.222-22", "Nome", LocalDate.of(1961, 02, 12), Classificacao.VIP, 100);
        Assento assento = new Assento(5L, "5A", false, true);

        assertThrows(AssentoEmergenciaException.class, () -> service.validarAssento(assento, passageiro, request.getMalasDespachadas()));
    }

    @Test
    @DisplayName("Quando somado as milhas de passageiro com as milhas de categoria, deve retornar o valor de milhas atualizado corretamente")
    void milhas() {
        Passageiro passageiro1 = new Passageiro(2L, "222.222.222-22", "Nome", LocalDate.of(1961, 02, 12), Classificacao.VIP, 100);
        Passageiro passageiro2 = new Passageiro(2L, "333.333.333-33", "Nome", LocalDate.of(1962, 06, 22), Classificacao.BRONZE, 50);
        passageiro1.setMilhas(passageiro1.getMilhas() + passageiro1.getClassificacao().getValor());
        passageiro2.setMilhas(passageiro2.getMilhas() + passageiro2.getClassificacao().getValor());

        assertNotNull(passageiro1.getMilhas());
        assertEquals(200, passageiro1.getMilhas());
        assertNotNull(passageiro2.getMilhas());
        assertEquals(80, passageiro2.getMilhas());
    }
}




