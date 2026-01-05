package com.cronograma.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RegisterUserService registerService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO dto) {

        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @RequestBody @Valid UserRequestDTO dto) {

        return ResponseEntity.ok(registerService.register(dto));
    }
}