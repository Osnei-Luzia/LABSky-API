package com.backend.labskyapi.controller;

import com.backend.labskyapi.models.Assento;
import com.backend.labskyapi.services.AssentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/assentos")
public class AssentoController {
    private final AssentoService service;
    public AssentoController(AssentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Assento>> buscarAssentos(){
        return ResponseEntity.ok().body(service.procurarAssento());
    }
}
