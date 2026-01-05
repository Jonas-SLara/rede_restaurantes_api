package com.cronograma.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronograma.demo.dto.request.CreateUnidadeRequestDTO;
import com.cronograma.demo.service.unidades.CreateUnidadeService;
import com.cronograma.demo.service.unidades.GetUnidadesService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/unidades")
@RequiredArgsConstructor
public class UnidadeController {

    private final CreateUnidadeService createService;
    private final GetUnidadesService getService;

    @PostMapping()
    public ResponseEntity<?> postUnidade(@Valid @RequestBody CreateUnidadeRequestDTO requestDTO) {
        return ResponseEntity.ok().body(createService.execute(requestDTO));
    }

    @GetMapping()
    public ResponseEntity<?> getAllUnidades() {
        return ResponseEntity.ok().body(getService.execute());
    }
    
}