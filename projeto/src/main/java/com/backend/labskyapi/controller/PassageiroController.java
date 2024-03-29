package com.backend.labskyapi.controller;

import com.backend.labskyapi.controller.dtos.PassageiroCPFResponseDTO;
import com.backend.labskyapi.controller.dtos.PassagemRequestDTO;
import com.backend.labskyapi.controller.dtos.PassagemResponseDTO;
import com.backend.labskyapi.controller.dtos.PassageiroResponseDTO;
import com.backend.labskyapi.services.PassageiroService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PassageiroCPFResponseDTO> buscarPassageirosByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok().body(service.procurarPassageirosByCpf(cpf));
    }

    @PostMapping("/confirmacao")
    public ResponseEntity<PassagemResponseDTO> confirmarPassageiro(@RequestBody @Validated PassagemRequestDTO request) {
        return ResponseEntity.ok().body(service.confirmarPassageiro(request));
    }
}
