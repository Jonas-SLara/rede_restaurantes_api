package com.cronograma.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronograma.demo.dto.request.LoginRequestDTO;
import com.cronograma.demo.dto.request.UserRequestDTO;
import com.cronograma.demo.dto.response.LoginResponseDTO;
import com.cronograma.demo.dto.response.UserResponseDTO;
import com.cronograma.demo.service.users.AuthService;
import com.cronograma.demo.service.users.RegisterUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(
    name = "operações relacionadas a autenticação do usuario",
    description = "fazer login e registrar na plataforma"
)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RegisterUserService registerService;

    @Operation(
        summary = "fazer login na plataforma, receber token"
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO dto) {

        return ResponseEntity.ok(authService.login(dto));
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(
        summary = "Se registrar na plataforma"
    )
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @RequestBody @Valid UserRequestDTO dto) {

        return ResponseEntity.ok(registerService.register(dto));
    }
}