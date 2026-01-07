package com.cronograma.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronograma.demo.dto.request.CreateUnidadeRequestDTO;
import com.cronograma.demo.service.unidades.CreateUnidadeService;
import com.cronograma.demo.service.unidades.GetUnidadesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/unidades")
@Tag(
    name = "operações relacionadas a unidades",
    description = "unidades são restaurantes cadastrados por um adm na plataforma"
)
@RequiredArgsConstructor
public class UnidadeController {

    private final CreateUnidadeService createService;
    private final GetUnidadesService getService;

    @Operation(
        summary = "Criar uma nova unidade"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping()
    public ResponseEntity<?> postUnidade(@Valid @RequestBody CreateUnidadeRequestDTO requestDTO) {
        return ResponseEntity.ok().body(createService.execute(requestDTO));
    }

    @Operation(
        summary = "Listar todas as unidades(restaurantes) cadastradas"
    )
    @GetMapping()
    public ResponseEntity<?> getAllUnidades() {
        return ResponseEntity.ok().body(getService.execute());
    }
    
}