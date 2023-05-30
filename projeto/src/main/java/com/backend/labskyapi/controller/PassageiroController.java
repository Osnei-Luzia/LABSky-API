package com.backend.labskyapi.controller;

import com.backend.labskyapi.controller.dtos.PassageiroResponseDTO;
import com.backend.labskyapi.services.PassageiroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/passageiros")
public class PassageiroController {
    private final PassageiroService service;

    public PassageiroController(PassageiroService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<PassageiroResponseDTO>> buscarPassageiros() {
        return ResponseEntity.ok().body(service.procurarPassageiros());
    }
    @GetMapping("/{cpf}")
    public ResponseEntity<PassageiroResponseDTO> buscarPassageirosByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok().body(service.procurarPassageirosByCpf(cpf));
    }
}
