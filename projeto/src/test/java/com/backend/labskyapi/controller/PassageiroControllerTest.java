package com.backend.labskyapi.controller;

import com.backend.labskyapi.controller.dtos.PassagemRequestDTO;
import com.backend.labskyapi.services.AssentoService;
import com.backend.labskyapi.services.PassageiroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class PassageiroControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PassageiroService service;
    @MockBean
    private AssentoService assentoService;

    @Test
    @DisplayName("Quando não há registros, deve retornar lista vazia")
    void buscarTodos_vazio() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/passageiros")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(Matchers.empty())));
    }

    @Test
    @DisplayName("Quando houver valores inválidos no request, deve retornar status Bad Request")
    void inserir() throws  Exception{
        PassagemRequestDTO request = new PassagemRequestDTO("111.111.111-11",null,true);
        String requestJSON = objectMapper.writeValueAsString(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/passageiros/confirmacao").content(requestJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}